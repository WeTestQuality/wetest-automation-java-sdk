package com.cloudtestapi.common;

import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.common.profile.HttpProfile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.net.ssl.SSLContext;
import org.apache.commons.logging.Log;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class AbstractClient {

    public static final int HTTP_RSP_OK = 200;
    public Gson gson;
    private Credential credential;
    private ClientProfile profile;
    private TCLog log;

    public AbstractClient(
            Credential credential,
            ClientProfile clientProfile) {
        this.credential = credential;
        this.profile = clientProfile;
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        this.log = new TCLog(getClass().getName(), profile.isDebug());
        warmup();
    }

    protected String internalRequest(AbstractRequest request)
            throws CloudTestSDKException {
        Response okRsp;
        String sm = this.profile.getSignMethod();

        if (sm.equals(ClientProfile.SIGN_SHA1) || sm.equals(ClientProfile.SIGN_SHA256)) {
            okRsp = doRequest(request);
        } else {
            throw new CloudTestSDKException(
                    "Signature method " + sm + " is invalid or not supported yet.");
        }

        return parseErrorFromResponse(okRsp);
    }

    private String parseErrorFromResponse(Response okRsp) throws CloudTestSDKException {
        String respBody;
        try {
            respBody = okRsp.body().string();
        } catch (IOException e) {
            String msg = "Cannot transfer response body to string, because Content-Length is too large, "
                    + "or Content-Length and stream length disagree.";
            log.getLogger().info(msg);
            throw new CloudTestSDKException(msg);
        }

        if (okRsp.code() != AbstractClient.HTTP_RSP_OK) {
            String msg = "response code is " + okRsp.code() + ", not 200";
            log.info(msg);
            log.info("resp body:" + respBody);
            throw new CloudTestSDKException(msg);
        }

        JsonResponseErrModel errResp;
        try {
            Type errType = new TypeToken<JsonResponseErrModel>() {
            }.getType();
            errResp = gson.fromJson(respBody, errType);
        } catch (JsonSyntaxException e) {
            String msg = "json is not a valid representation for an object of type";
            log.info(msg);
            throw new CloudTestSDKException(msg);
        }
        if (errResp != null && errResp.code != 0) {
            throw new CloudTestSDKException(
                    errResp.message + ";" + errResp.msg,
                    errResp.code,
                    errResp.traceId);
        }
        return respBody;
    }

    private Response doRequest(AbstractRequest request)
            throws CloudTestSDKException {
        // request object -> path param map<string, string>
        HashMap<String, String> pathMap = new HashMap<>();
        request.toPathParamMap(pathMap, "");
        // path param map<string, string> -> path str
        String path = request.getPath();
        for (Map.Entry<String, String> entry : pathMap.entrySet()) {
            if ("".equals(entry.getValue())) {
                path = path.replaceAll(entry.getKey() + "/", entry.getValue());
            }
            path = path.replaceAll(entry.getKey(), entry.getValue());
        }

        // request object -> query param map<string, string[]>
        HashMap<String, Object> queryMap = new HashMap<>();
        request.toQueryParamMap(queryMap, "");

        // sign param
        this.signParam(queryMap, "/" + this.getClientProfile().getHttpProfile().getToolPath() + "/"
                + request.getVersion() + path, request.getHttpMethod());

        HttpConnection conn =
                new HttpConnection(
                        this.profile.getHttpProfile().getConnTimeout(),
                        this.profile.getHttpProfile().getReadTimeout(),
                        this.profile.getHttpProfile().getWriteTimeout());
        conn.addInterceptors(log);
        this.trySetProxy(conn);
        String reqMethod = request.getHttpMethod();
        if ("".equals(reqMethod)) {
            reqMethod = this.profile.getHttpProfile().getReqMethod();
        }

        // query param map<string, string[]> -> query string
        String queryStr = getQueryStr(queryMap);
        String url =
                this.profile.getHttpProfile().getProtocol() + this.getRootDomain() + "/"
                        + this.profile.getHttpProfile().getToolPath() + "/"
                        + request.getVersion() + path + "?" + queryStr;
        if (request instanceof AbstractUploadRequest) {
            AbstractUploadRequest uploadRequest = (AbstractUploadRequest) request;
            return conn.postFormRequest(url, uploadRequest.getBody(), uploadRequest.getFieldName(),
                    uploadRequest.getFileName(), uploadRequest.getFileMime());
        }

        String jsonStr = request.toJsonBody();
        switch (reqMethod) {
            case HttpProfile.REQ_GET:
                return conn.getRequest(url);
            case HttpProfile.REQ_POST:
                // request object to json str
                if (jsonStr == null) {
                    jsonStr = gson.toJson(request);
                }
                return conn.postRequest(url, jsonStr);
            case HttpProfile.REQ_PUT:
                if (jsonStr == null) {
                    jsonStr = gson.toJson(request);
                }
                return conn.putRequest(url, jsonStr);
            case HttpProfile.REQ_DELETE:
                return conn.deleteRequest(url);
            default:
                throw new CloudTestSDKException("Method only support (GET, POST, PUT, DELETE)");
        }
    }

    private String getQueryStr(HashMap<String, Object> queryMap) {
        List<NameValuePair> parameters = new ArrayList<>();
        queryMap.forEach((key, value) -> {
                    parameters.add(new BasicNameValuePair(key, String.valueOf(value)));
//                    if (values.length > 0) {
//                        for (String value : values) {
//                            parameters.add(new BasicNameValuePair(key, value));
//                        }
//                    }
                }
        );
        return URLEncodedUtils.format(parameters, "utf-8");
    }

    private String getRootDomain() {
        return this.profile.getHttpProfile().getRootDomain();
    }

    private void trySetProxy(HttpConnection conn) {
        String host = this.profile.getHttpProfile().getProxyHost();
        int port = this.profile.getHttpProfile().getProxyPort();

        if (host == null || host.isEmpty()) {
            return;
        }
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
        conn.setProxy(proxy);

        final String username = this.profile.getHttpProfile().getProxyUsername();
        final String password = this.profile.getHttpProfile().getProxyPassword();
        if (username == null || username.isEmpty()) {
            return;
        }
        conn.setAuthenticator(
                new Authenticator() {
                    @Override
                    public Request authenticate(Proxy proxy, Response response) throws IOException {
                        String credential = Credentials.basic(username, password);
                        return response
                                .request()
                                .newBuilder()
                                .header("Proxy-Authorization", credential)
                                .build();
                    }

                    @Override
                    public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
                        return authenticate(proxy, response);
                    }
                });
    }

    private void signParam(Map<String, Object> param, String path, String method)
            throws CloudTestSDKException {
        Random rd = new Random();
        param.put("t", String.valueOf(System.currentTimeMillis() / 1000));
        param.put("nonce", String.valueOf(rd.nextInt(99999)));
        if (this.credential.getSecretId() != null && (!this.credential.getSecretId().isEmpty())) {
            param.put("secret_id", this.credential.getSecretId());
            param.put("secretid", this.credential.getSecretId());
        }
        String signSource;

        try {
            signSource = Sign.getSignSource(new TreeMap<>(param), method, path);
        } catch (UnsupportedEncodingException e) {
            throw new CloudTestSDKException(e.getClass().getName() + "-" + e.getMessage());
        }

        String sign =
                Sign.sign(this.credential.getSecretKey(), signSource, this.profile.getSignMethod());
        param.put("sign", sign);

    }


    /**
     * warm up, try to avoid unnecessary cost in the first request
     */
    private void warmup() {
        try {
            // it happens in SDK signature process.
            // first invoke costs around 250 ms.
            Mac.getInstance("HmacSHA1");
            Mac.getInstance("HmacSHA256");
            // it happens inside okhttp, but I think any https framework/package will do the same.
            // first invoke costs around 150 ms.
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, null);
        } catch (Exception e) {
            // ignore but print message to console
            e.printStackTrace();
        }
    }

    public Log getLogger() {
        return log.getLogger();
    }

    public void setLogger(Log logger) {
        this.log.setLogger(logger);
    }

    public ClientProfile getClientProfile() {
        return this.profile;
    }

    public void setClientProfile(ClientProfile profile) {
        this.profile = profile;
    }

    public Credential getCredential() {
        return this.credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public void setProfile(ClientProfile profile) {
        this.profile = profile;
    }
}

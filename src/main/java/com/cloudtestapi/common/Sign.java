package com.cloudtestapi.common;

import com.cloudtestapi.common.exception.CloudTestSDKException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Sign {

    private static final Charset UTF8 = StandardCharsets.UTF_8;

    public static String sign(String secretKey, String sigStr, String sigMethod)
            throws CloudTestSDKException {
        String sig = null;
        try {
            Mac mac = Mac.getInstance(sigMethod);
            byte[] hash;
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(UTF8), mac.getAlgorithm());

            mac.init(secretKeySpec);
            hash = mac.doFinal(sigStr.getBytes(UTF8));
            sig = Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new CloudTestSDKException(e.getClass().getName() + "-" + e.getMessage());
        }
        return sig;
    }

    public static String getSignSource(
            TreeMap<String, Object> requestParams, String reqMethod,  String path)
            throws UnsupportedEncodingException {
        StringBuilder paramStr = new StringBuilder();
        requestParams.forEach((key, value) -> {
            paramStr.append(String.format("&%s=%s", key, value));
        });
        if (paramStr.length() > 0 && paramStr.indexOf("&") == 0) {
            paramStr.deleteCharAt(0);
        }

        return String.format("%s&%s&%s", reqMethod,
                URLEncoder.encode(path, "utf-8"),
                URLEncoder.encode(paramStr.toString(), "utf-8"));
    }
}

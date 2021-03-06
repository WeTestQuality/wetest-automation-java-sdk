package com.cloudtestapi.common.profile;

public class HttpProfile {

    public static final String REQ_HTTPS = "https://";

    public static final String REQ_HTTP = "http://";

    public static final String REQ_POST = "POST";

    public static final String REQ_GET = "GET";

    public static final String REQ_PUT = "PUT";

    public static final String REQ_DELETE = "DELETE";

    /**
     * Time unit, 1 minute, equals 60 seconds.
     */
    public static final int TM_MINUTE = 60;

    private String reqMethod;

    /**
     * root domain means endpoint without service name
     */
    private String rootDomain;

    /**
     * HTTPS or HTTP
     */
    private String protocol;

    /**
     * Read timeout in seconds.
     */
    private int readTimeout;

    /**
     * Write timeout in seconds
     */
    private int writeTimeout;

    /**
     * Connect timeout in seconds
     */
    private int connTimeout;

    /**
     * http proxy host
     */
    private String proxyHost;

    /**
     * http proxy port
     */
    private int proxyPort;

    /**
     * http proxy user name
     */
    private String proxyUser;

    /**
     * http proxy password
     */
    private String proxyPasswd;


    private String toolPath;

    public HttpProfile() {
        this.reqMethod = HttpProfile.REQ_POST;
        this.rootDomain = "api.tool.cloudtest.woa.com";
        this.toolPath = "@todo";
        this.protocol = HttpProfile.REQ_HTTPS;
        this.readTimeout = 0;
        this.writeTimeout = 0;
        this.connTimeout = HttpProfile.TM_MINUTE;
    }

    public String getReqMethod() {
        return this.reqMethod;
    }

    /**
     * Set request method, GET or POST or PUT or DELETE
     *
     * @param reqMethod GET or POST or PUT or DELETE
     */
    public void setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    /**
     * Set read timeout value.
     *
     * @param readTimeout A integer represents time in seconds.
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getWriteTimeout() {
        return this.writeTimeout;
    }

    /**
     * Set write timeout value.
     *
     * @param writeTimeout A integer represents time in seconds.
     */
    public void setWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public int getConnTimeout() {
        return this.connTimeout;
    }

    /**
     * Set connect timeout value.
     *
     * @param connTimeout A integer represents time in seconds.
     */
    public void setConnTimeout(int connTimeout) {
        this.connTimeout = connTimeout;
    }

    public String getProtocol() {
        return this.protocol;
    }

    /**
     * Set request protocol.
     *
     * @param protocol https:// or http://
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyUser() {
        return proxyUser;
    }

    public void setProxyUser(String proxyUser) {
        this.proxyUser = proxyUser;
    }

    public String getProxyPasswd() {
        return proxyPasswd;
    }

    public void setProxyPasswd(String proxyPasswd) {
        this.proxyPasswd = proxyPasswd;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public String getRootDomain() {
        return rootDomain;
    }

    public void setRootDomain(String rootDomain) {
        this.rootDomain = rootDomain;
    }

    public String getToolPath() {
        return toolPath;
    }

    public void setToolPath(String toolPath) {
        this.toolPath = toolPath;
    }
}

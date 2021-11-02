package com.cloudtestapi.common;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;

public abstract class AbstractRequest {

    private String path = "/";

    @Expose(serialize = false)
    @SerializedName("request_version")
    private String version = "v1";

    private String httpMethod = "POST";

    protected abstract byte[] toBody();

    protected abstract void toQueryParamMap(HashMap<String, Object> map, String prefix);

    protected abstract void toPathParamMap(HashMap<String, String> map, String prefix);

    protected void withApiInfo(String version, String path) {
        setPath(path);
        setVersion(version);
    }

    protected String getPath() {
        return this.path;
    }

    protected void setPath(String path) {
        this.path = path;
    }

    protected String getVersion() {
        return this.version;
    }

    protected void setVersion(String version) {
        this.version = version;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
}

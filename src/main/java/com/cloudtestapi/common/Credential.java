package com.cloudtestapi.common;

public class Credential {

    private String secretId;

    private String secretKey;

    public Credential(String secretId, String secretKey) {
        this.secretId = secretId;
        this.secretKey = secretKey;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSecretId() {
        return this.secretId;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

}

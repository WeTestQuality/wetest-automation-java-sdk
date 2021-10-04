package com.cloudtestapi.common.profile;

public class ClientProfile {

    /**
     * Signature process version 1, with HmacSHA1.
     */
    public static final String SIGN_SHA1 = "HmacSHA1";

    /**
     * Signature process version 1, with HmacSHA256.
     */
    public static final String SIGN_SHA256 = "HmacSHA256";

    private HttpProfile httpProfile;

    private String signMethod;


    private boolean debug;

    public ClientProfile(String signMethod, HttpProfile httpProfile) {
        if (signMethod == null || signMethod.isEmpty()) {
            signMethod = SIGN_SHA256;
        }
        this.signMethod = signMethod;
        this.httpProfile = httpProfile;
        this.setDebug(false);
    }

    public ClientProfile(String signMethod) {
        this(signMethod, new HttpProfile());
    }

    public ClientProfile() {
        this(ClientProfile.SIGN_SHA256, new HttpProfile());
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public void setHttpProfile(HttpProfile httpProfile) {
        this.httpProfile = httpProfile;
    }

    public String getSignMethod() {
        return this.signMethod;
    }

    public HttpProfile getHttpProfile() {
        return this.httpProfile;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}

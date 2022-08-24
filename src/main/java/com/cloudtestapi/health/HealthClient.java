package com.cloudtestapi.health;

import com.cloudtestapi.common.AbstractClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.health.models.HealthRequest;

public class HealthClient extends AbstractClient {

    public HealthClient(Credential credential, ClientProfile clientProfile) {
        super(credential, clientProfile);
    }

    /**
     * check server health status
     * @return true if server is healthy
     * @throws CloudTestSDKException when server cannot reached
     */
    public Boolean checkServerHealth() throws CloudTestSDKException {
        HealthRequest req = new HealthRequest();
        this.internalRequest(req);
        return true;
    }
}

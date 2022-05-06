package com.cloudtestapi;

import com.cloudtestapi.account.AccountClient;
import com.cloudtestapi.common.AbstractClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.device.DeviceClient;
import com.cloudtestapi.slot.SlotClient;
import com.cloudtestapi.test.TestClient;
import com.cloudtestapi.upload.UploadClient;

public class CTClient extends AbstractClient {

    public UploadClient upload;

    public DeviceClient device;

    public TestClient test;

    public AccountClient account;

    public SlotClient slot;

    public CTClient(Credential credential) throws CloudTestSDKException {
        this(credential, new ClientProfile());
    }

    public CTClient(Credential credential, ClientProfile clientProfile) throws CloudTestSDKException {
        super(credential, clientProfile);
        this.upload = new UploadClient(credential, clientProfile);
        this.device = new DeviceClient(credential, clientProfile);
        this.test = new TestClient(credential, clientProfile);
        this.account = new AccountClient(credential, clientProfile);
        this.slot = new SlotClient(credential, clientProfile);
        this.checkCredential(credential);
    }

    private void checkCredential(Credential credential) throws CloudTestSDKException {
        if (credential == null || credential.getSecretId() == null || credential.getSecretKey() == null
                || credential.getSecretId().length() == 0 || credential.getSecretKey().length() == 0) {
            throw new CloudTestSDKException("invalid credential");
        }
    }
}

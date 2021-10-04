package com.cloudtestapi.device;

import com.cloudtestapi.common.AbstractClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.device.models.Devices;
import com.cloudtestapi.device.models.GetDevicesResponse;
import com.cloudtestapi.device.models.GetDevicesByCloudIdRequest;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


public class DeviceClient extends AbstractClient {
    public DeviceClient(Credential credential) {
        this(credential, new ClientProfile());
    }

    public DeviceClient(Credential credential, ClientProfile clientProfile) {
        super(credential, clientProfile);
    }

    public GetDevicesResponse getDevicesById(GetDevicesByCloudIdRequest request) throws CloudTestSDKException {
        GetDevicesResponse rsp = null;
        String rspStr = "";
        try{
            Type type = new TypeToken<GetDevicesResponse>(){}.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        }catch (JsonSyntaxException e){
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp;
    }
}

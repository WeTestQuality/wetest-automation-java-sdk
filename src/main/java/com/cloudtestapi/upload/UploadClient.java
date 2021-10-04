package com.cloudtestapi.upload;

import com.cloudtestapi.common.AbstractClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.JsonResponseModel;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.upload.models.App;
import com.cloudtestapi.upload.models.GetAppInfoRequest;
import com.cloudtestapi.upload.models.GetAppInfoResponse;
import com.cloudtestapi.upload.models.GetScriptInfoRequest;
import com.cloudtestapi.upload.models.GetScriptInfoResponse;
import com.cloudtestapi.upload.models.Script;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class UploadClient extends AbstractClient {
    public UploadClient(Credential credential) {
        this(credential, new ClientProfile());
    }

    public UploadClient(Credential credential, ClientProfile clientProfile) {
        super(credential, clientProfile);
    }

    /**
     * 获取App信息
     * @param request GetAppInfoRequest
     * @return App
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public App getAppInfo(GetAppInfoRequest request) throws CloudTestSDKException {
        GetAppInfoResponse rsp = null;
        String rspStr = "";
        try {
            Type type = new TypeToken<GetAppInfoResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.app;
    }

    /**
     * 获取script信息
     * @param request GetScriptInfoRequest
     * @return Script
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public Script getScriptInfo(GetScriptInfoRequest request) throws CloudTestSDKException{
        GetScriptInfoResponse rsp = null;
        String rspStr = "";
        try{
            Type type = new TypeToken<GetScriptInfoResponse>(){}.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        }catch (JsonSyntaxException e){
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.script;
    }

}

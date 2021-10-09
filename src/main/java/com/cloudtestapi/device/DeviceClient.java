package com.cloudtestapi.device;

import com.cloudtestapi.common.AbstractClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.JsonResponseModel;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.device.models.DeviceBasicInfo;
import com.cloudtestapi.device.models.GetDeviceStateRequest;
import com.cloudtestapi.device.models.GetDeviceStateResponse;
import com.cloudtestapi.device.models.GetDevicesByCloudIdRequest;
import com.cloudtestapi.device.models.GetDevicesResponse;
import com.cloudtestapi.device.models.GetModelListRequest;
import com.cloudtestapi.device.models.ModelList;
import com.cloudtestapi.device.models.ReportDeviceOfflineRequest;
import com.cloudtestapi.device.models.SoftRebootDeviceRequest;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import javax.jws.WebParam.Mode;
import org.omg.PortableInterceptor.Interceptor;


public class DeviceClient extends AbstractClient {
    public DeviceClient(Credential credential) {
        this(credential, new ClientProfile());
    }

    public DeviceClient(Credential credential, ClientProfile clientProfile) {
        super(credential, clientProfile);
    }


    private GetDevicesResponse getDevicesByCloudId(GetDevicesByCloudIdRequest request) throws CloudTestSDKException {
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


    /**
     * 通过云ID拉取设备列表
     * @param cloudId int
     * @return GetDevicesResponse
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public GetDevicesResponse getDevicesByCloudId(int cloudId) throws CloudTestSDKException{
        GetDevicesByCloudIdRequest request = new GetDevicesByCloudIdRequest();
        request.setCloudId(cloudId);
        return this.getDevicesByCloudId(request);
    }

    private DeviceBasicInfo getDeviceState(GetDeviceStateRequest request) throws CloudTestSDKException{
        GetDeviceStateResponse rsp;
        String rspStr= "";
        try{
            Type type = new TypeToken<GetDeviceStateResponse>(){}.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        }catch (JsonSyntaxException e){
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.deviceInfo;
    }

    /**
     * 获取设备状态
     * @param deviceId Integer
     * @param cloudId Integer
     * @return DeviceBasicInfo
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public DeviceBasicInfo getDeviceState(Integer deviceId, Integer cloudId) throws CloudTestSDKException{
        GetDeviceStateRequest request = new GetDeviceStateRequest();
        request.setDeviceId(deviceId);
        request.setCloudId(cloudId);
        return this.getDeviceState(request);
    }


    /**
     * 上报设备掉线
     * @param request ReportDeviceOfflineRequest
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public void reportDeviceOffline(ReportDeviceOfflineRequest request) throws CloudTestSDKException{
        this.internalRequest(request);
    }

    /**
     * 重启设备
     * @param request ReportDeviceOfflineRequest
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public void rebootDevice(ReportDeviceOfflineRequest request) throws CloudTestSDKException{
        this.internalRequest(request);
    }

    /**
     * 软重启设备
     * @param request SoftRebootDeviceRequest
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public void softRebootDevice(SoftRebootDeviceRequest request) throws CloudTestSDKException{
        this.internalRequest(request);
    }

    /**
     * 获取设备机型列表
     * @return ModelList 机型列表
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public ModelList[] getModelList() throws CloudTestSDKException{
        JsonResponseModel<ModelList[]> rsp = null;
        String rspStr = "";
        try{
            GetModelListRequest request = new GetModelListRequest();
            Type type = new TypeToken<JsonResponseModel<ModelList[]>>(){}.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        }catch (JsonSyntaxException e){
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.data;
    }

}

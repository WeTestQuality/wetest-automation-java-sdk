package com.cloudtestapi.device;

import com.cloudtestapi.account.models.Project;
import com.cloudtestapi.common.AbstractClient;
import com.cloudtestapi.common.Constants;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.JsonResponseModel;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.device.models.DeviceBasicInfo;
import com.cloudtestapi.device.models.DeviceListInfo;
import com.cloudtestapi.device.models.GetDeviceStateRequest;
import com.cloudtestapi.device.models.GetDeviceStateResponse;
import com.cloudtestapi.device.models.GetDevicesByCloudIdRequest;
import com.cloudtestapi.device.models.GetDevicesResponse;
import com.cloudtestapi.device.models.GetModelListInfoRequest;
import com.cloudtestapi.device.models.GetModelListRequest;
import com.cloudtestapi.device.models.GetProjectModelListRequest;
import com.cloudtestapi.device.models.ModelList;
import com.cloudtestapi.device.models.ModelListInfo;
import com.cloudtestapi.device.models.ModelListInfoBase;
import com.cloudtestapi.device.models.ProjectModelList;
import com.cloudtestapi.device.models.ReportDeviceOfflineRequest;
import com.cloudtestapi.device.models.SoftRebootDeviceRequest;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;


public class DeviceClient extends AbstractClient {
    private String wdbPath = "wdb";

    public DeviceClient(Credential credential) {
        this(credential, new ClientProfile());
    }

    public DeviceClient(Credential credential, ClientProfile clientProfile) {
        super(credential, clientProfile);
    }

    private GetDevicesResponse getDevicesByCloudId(GetDevicesByCloudIdRequest request) throws CloudTestSDKException {
        GetDevicesResponse rsp = null;
        String rspStr = "";
        try {
            Type type = new TypeToken<GetDevicesResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp;
    }


    /**
     * Get device list by cloud id
     *
     * @param cloudId int
     * @return GetDevicesResponse
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public GetDevicesResponse getDevicesByCloudId(int cloudId) throws CloudTestSDKException {
        GetDevicesByCloudIdRequest request = new GetDevicesByCloudIdRequest();
        request.setCloudId(cloudId);
        return this.getDevicesByCloudId(request);
    }

    private DeviceBasicInfo getDeviceState(GetDeviceStateRequest request) throws CloudTestSDKException {
        GetDeviceStateResponse rsp;
        String rspStr = "";
        try {
            Type type = new TypeToken<GetDeviceStateResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.deviceInfo;
    }

    /**
     * Get device status
     *
     * @param deviceId Integer
     * @param cloudId Integer
     * @return DeviceBasicInfo
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public DeviceBasicInfo getDeviceState(Integer deviceId, Integer cloudId) throws CloudTestSDKException {
        GetDeviceStateRequest request = new GetDeviceStateRequest();
        request.setDeviceId(deviceId);
        request.setCloudId(cloudId);
        return this.getDeviceState(request);
    }


    /**
     * 上报设备掉线
     *
     * @param request ReportDeviceOfflineRequest
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public void reportDeviceOffline(ReportDeviceOfflineRequest request) throws CloudTestSDKException {
        this.internalRequest(request);
    }

    /**
     * Reboot device
     *
     * @param request ReportDeviceOfflineRequest
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public void rebootDevice(ReportDeviceOfflineRequest request) throws CloudTestSDKException {
        this.internalRequest(request);
    }

    /**
     * Soft-reboot device
     *
     * @param request SoftRebootDeviceRequest
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public void softRebootDevice(SoftRebootDeviceRequest request) throws CloudTestSDKException {
        this.internalRequest(request);
    }

    /**
     * Get device model list
     *
     * @return ModelList device model list
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public ModelList[] getModelList(String projectId) throws CloudTestSDKException {
        JsonResponseModel<ModelList[]> rsp = null;
        String rspStr = "";
        try {
            GetModelListRequest request = new GetModelListRequest();
            request.setProjectId(projectId);
            Type type = new TypeToken<JsonResponseModel<ModelList[]>>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.data;
    }

    /**
     * Get device model list configured in web controller
     * @param projectId
     * @return
     * @throws CloudTestSDKException
     */
    public ProjectModelList[] getProjectModelList(String projectId, Constants.ModelListFilterType filterType) throws CloudTestSDKException {
        JsonResponseModel<ProjectModelList[]> rsp = null;
        String rspStr = "";
        try {
            GetProjectModelListRequest request = new GetProjectModelListRequest();
            request.setProjectId(projectId);
            if (filterType != null) {
                request.setFilterType(filterType.getCode());
            }
            Type type = new TypeToken<JsonResponseModel<ProjectModelList[]>>(){}.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.data;
    }

    public <T extends ModelListInfoBase> T[] getProjectModelListInfo(Integer listId, Constants.ModelListFilterType filterType) throws CloudTestSDKException {
        JsonResponseModel<T[]> rsp = null;
        String rspStr = "";
        try {
            GetModelListInfoRequest request = new GetModelListInfoRequest();
            request.setListId(listId);
            rspStr = this.internalRequest(request);
            if (Constants.ModelListFilterType.MODEL.equals(filterType)) {
                Type type = new TypeToken<JsonResponseModel<ModelListInfo[]>>(){}.getType();
                rsp = gson.fromJson(rspStr, type);
            } else if (Constants.ModelListFilterType.DEVICE.equals(filterType)) {
                Type type = new TypeToken<JsonResponseModel<DeviceListInfo[]>>(){}.getType();
                rsp = gson.fromJson(rspStr, type);
            } else {
                throw new CloudTestSDKException("invalid filterType while querying model list info");
            }
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.data;
    }

    public String wdbConnect(String address,String token ,int timeOut) throws IOException, InterruptedException,CloudTestSDKException {
        String  cmd = String.format("%s connect %s -t=%s",this.wdbPath , address, token);
        Process p = Runtime.getRuntime().exec(cmd);
        boolean res = p.waitFor(timeOut, TimeUnit.SECONDS);
        if(!res) {
            throw  new CloudTestSDKException("wdb connect timeout");
        }
        InputStream inputStream = p.getInputStream();
        byte[] data = new byte[1024];
        StringBuilder result = new StringBuilder();
        while(inputStream.read(data) != -1) {
            result.append(new String(data));
        }
        if (result.toString().equals("")) {
            InputStream errorStream = p.getErrorStream();
            while(errorStream.read(data) != -1) {
                result.append(new String(data));
            }
        }
        return result.toString();
    }

    public void setWdbPath(String path) {
        this.wdbPath = path;
    }
}

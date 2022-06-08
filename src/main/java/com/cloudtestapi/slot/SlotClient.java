package com.cloudtestapi.slot;

import com.cloudtestapi.common.AbstractClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.JsonResponseModel;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.device.models.DeviceBasicInfo;
import com.cloudtestapi.device.models.GetDeviceStateResponse;
import com.cloudtestapi.slot.models.*;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class SlotClient extends AbstractClient {
    public SlotClient(Credential credential) {
        this(credential, new ClientProfile());
    }

    public SlotClient(Credential credential, ClientProfile clientProfile) {
        super(credential, clientProfile);
    }

    /**
     * slotListInfo
     * @param slotOutAddr slot address
     * @return   SlotInfo[]
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public SlotInfo[] slotListInfo(String slotOutAddr) throws CloudTestSDKException {
        JsonResponseModel<SlotListInfoResponse> rsp = null;
        SlotListInfoRequest request = new SlotListInfoRequest();
        request.setSlotOutAddr(slotOutAddr);
        String rspStr = "";
        try{
            Type type = new TypeToken< JsonResponseModel<SlotListInfoResponse>>(){}.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        }catch (JsonSyntaxException e){
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.data.list;
    }

    /**
     * Get a list of replaceable models
     * @param phoneTypeList
     * @return ModelInfo[]
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public ModelInfo[] slotListModel(int[] phoneTypeList) throws CloudTestSDKException{
        JsonResponseModel<SlotListModelResponse> rsp = null;
        SlotListModelRequest request = new SlotListModelRequest();
        request.setPhoneTypeList(phoneTypeList);
        String rspStr = "";
        try{
            Type type = new TypeToken<JsonResponseModel<SlotListModelResponse>>(){}.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        }catch (JsonSyntaxException e){
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.data.list;
    }

    /**
     * Get a list of replaceable devices
     * @param phoneTypeList
     * @return DeviceBasicInfo[]
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public DeviceBasicInfo[] slotListDevice(int[] phoneTypeList) throws CloudTestSDKException{
        JsonResponseModel<SlotListDeviceResponse> rsp = null;
        SlotListDeviceRequest request = new SlotListDeviceRequest();
        request.setPhoneTypeList(phoneTypeList);
        String rspStr = "";
        try{
            Type type = new TypeToken<JsonResponseModel<SlotListDeviceResponse>>(){}.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        }catch (JsonSyntaxException e){
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.data.list;
    }

    /**
     * Applying to replace a slot-bound device
     * @param slotOutAddr
     * @param modelId
     * @param deviceId
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public void slotSwitchStart(String slotOutAddr, int modelId, int deviceId) throws CloudTestSDKException{
        SlotSwitchStartRequest request = new SlotSwitchStartRequest();
        request.setModelId(modelId);
        request.setDeviceId(deviceId);
        request.setSlotOutAddr(slotOutAddr);
        this.internalRequest(request);
    }

    /**
     * Cancel device replacement
     * @param slotOutAddr
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public void slotSwitchCancel(String slotOutAddr) throws CloudTestSDKException{
        SlotSwitchCancelRequest request = new SlotSwitchCancelRequest();
        request.setSlotOutAddr(slotOutAddr);
        this.internalRequest(request);
    }

    /**
     * IPA re-signature
     * @param slotOutAddr
     * @param fileUrl
     * @return file_id
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public String resignIpa(String slotOutAddr, String fileUrl) throws CloudTestSDKException{
        ResignIpaRequest request = new ResignIpaRequest();
        request.setSlotOutAddr(slotOutAddr);
        request.setFileUrl(fileUrl);
        JsonResponseModel<ResignIpaResponse> rsp = null;
        String rspStr = "";
        try{
            Type type = new TypeToken<JsonResponseModel<ResignIpaResponse>>(){}.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        }catch (JsonSyntaxException e){
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.data.fileId;
    }

    /**
     * Query the re-signature status and obtain the re-signed ipa download address
     * @param slotOutAddr
     * @param fileId
     * @return ResignResult
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public ResignResult fetchResignResult(String slotOutAddr, String fileId) throws CloudTestSDKException{
        FetchResignResultRequest request = new FetchResignResultRequest();
        request.setFileId(fileId);
        request.setSlotOutAddr(slotOutAddr);
        JsonResponseModel<ResignResult> rsp = null;
        String rspStr = "";
        try{
            Type type = new TypeToken<JsonResponseModel<ResignResult>>(){}.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        }catch (JsonSyntaxException e){
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.data;
    }
}

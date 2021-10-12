package com.cloudtestapi.slot;

import com.cloudtestapi.common.AbstractClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.JsonResponseModel;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.device.models.GetDeviceStateResponse;
import com.cloudtestapi.slot.models.FetchResignResultRequest;
import com.cloudtestapi.slot.models.ModelInfo;
import com.cloudtestapi.slot.models.ResignIpaRequest;
import com.cloudtestapi.slot.models.ResignIpaResponse;
import com.cloudtestapi.slot.models.ResignResult;
import com.cloudtestapi.slot.models.SlotInfo;
import com.cloudtestapi.slot.models.SlotListInfoRequest;
import com.cloudtestapi.slot.models.SlotListInfoResponse;
import com.cloudtestapi.slot.models.SlotListModelRequest;
import com.cloudtestapi.slot.models.SlotListModelResponse;
import com.cloudtestapi.slot.models.SlotSwitchCancelRequest;
import com.cloudtestapi.slot.models.SlotSwitchStartRequest;
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
     * slotListInfo 获取已有的插槽状态, 查看插槽状态、地址和关联的设备
     * @param slotOutAddr 插槽地址
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
     * 获取可更换的机型列表
     * @param phoneTypeList 获取指定类型的机型0-安卓真机；1-iOS真机；2-安卓定制机；3-鸿蒙，不指定则获取全部类型的可更换机型
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
     * 申请更换插槽绑定的设备
     * @param slotOutAddr 指定需要更换的插槽
     * @param modelId 指定需要更换的机型，通过接口“获取可更换的机型列表”获得
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public void slotSwitchStart(String slotOutAddr, int modelId) throws CloudTestSDKException{
        SlotSwitchStartRequest request = new SlotSwitchStartRequest();
        request.setModelId(modelId);
        request.setSlotOutAddr(slotOutAddr);
        this.internalRequest(request);
    }

    /**
     * 取消更换设备
     * @param slotOutAddr 指定需要取消更换设备的插槽
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public void slotSwitchCancel(String slotOutAddr) throws CloudTestSDKException{
        SlotSwitchCancelRequest request = new SlotSwitchCancelRequest();
        request.setSlotOutAddr(slotOutAddr);
        this.internalRequest(request);
    }

    /**
     * IPA重签名
     * @param slotOutAddr 通过插槽地址指定需要签名的设备，插槽必须绑定了iOS设备，否则无法重签名
     * @param fileUrl 需要重签名的ipa包的下载地址
     * @return file_id, 标识重签名文件，用来查询重签名是否完成
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
     * 查询重签名状态，获取重签名后的ipa下载地址
     * @param slotOutAddr 通过插槽地址指定需要签名的设备，插槽必须绑定了iOS设备，否则无法重签名
     * @param fileId 通过接口“IPA重签名”获得，标识重签名文件，用来查询重签名是否完成
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

package com.cloudtestapi.upload.models;

import com.cloudtestapi.common.AbstractUploadRequest;
import com.cloudtestapi.common.profile.HttpProfile;
import java.util.HashMap;

public class UploadRequest extends AbstractUploadRequest {

    private String uploadId;

    private int chunkNum;

    private byte[] body;

    public UploadRequest() {
        this.setHttpMethod(HttpProfile.REQ_POST);
        this.withApiInfo("v1", "/files/:upload_id/chunks/:chunk_num");
    }

    @Override
    protected void toQueryParamMap(HashMap<String, Object> map, String prefix) {

    }

    @Override
    protected void toPathParamMap(HashMap<String, String> map, String prefix) {
        map.put(":upload_id", uploadId);
        map.put(":chunk_num", String.valueOf(chunkNum));
    }

    @Override
    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public int getChunkNum() {
        return chunkNum;
    }

    public void setChunkNum(int chunkNum) {
        this.chunkNum = chunkNum;
    }
}

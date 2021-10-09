package com.cloudtestapi.common;

import java.util.HashMap;

public abstract class AbstractUploadRequest extends AbstractRequest{
    private String fieldName;

    private String fileMime;

    private String fileName;

    @Override
    protected String toJsonBody() {
        return null;
    }

    protected abstract byte[] getBody();

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFileMime() {
        return fileMime;
    }

    public void setFileMime(String fileMime) {
        this.fileMime = fileMime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

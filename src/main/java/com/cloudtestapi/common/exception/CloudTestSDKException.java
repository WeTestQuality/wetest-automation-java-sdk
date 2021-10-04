package com.cloudtestapi.common.exception;

public class CloudTestSDKException extends Exception {

    private static final long serialVersionUID = 1L;

    private int errorCode;

    private String traceId;

    private static final int ErrorCodeBadRequest = 400;

    public CloudTestSDKException(String message) {
        this(message, ErrorCodeBadRequest, "");
    }

    public CloudTestSDKException(String message, int errorCode, String traceId) {
        super(message);
        this.errorCode = errorCode;
        this.traceId = traceId;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public static int getErrorCodeBadRequest() {
        return ErrorCodeBadRequest;
    }

    public String toString() {
        return "[CloudTestSDKException]"
                + "code: "
                + this.getErrorCode()
                + " message: "
                + this.getMessage()
                + " trace_id: "
                + this.getTraceId();
    }



}

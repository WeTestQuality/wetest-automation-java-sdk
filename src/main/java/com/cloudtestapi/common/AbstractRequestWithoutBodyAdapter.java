package com.cloudtestapi.common;

public abstract class AbstractRequestWithoutBodyAdapter extends AbstractRequest {

    @Override
    protected String toJsonBody() {
        return "";
    }
}

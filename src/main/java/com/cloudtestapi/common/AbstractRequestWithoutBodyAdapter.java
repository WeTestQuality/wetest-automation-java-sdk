package com.cloudtestapi.common;

public abstract class AbstractRequestWithoutBodyAdapter extends AbstractRequest {

    @Override
    protected byte[] toBody() {
        return null;
    }
}

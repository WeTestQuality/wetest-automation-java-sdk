package com.cloudtestapi.common;

public abstract class AbstractRequestWithoutSpecificBodyGenerator extends AbstractRequest {

    @Override
    protected byte[] toBody() {
        return null;
    }
}

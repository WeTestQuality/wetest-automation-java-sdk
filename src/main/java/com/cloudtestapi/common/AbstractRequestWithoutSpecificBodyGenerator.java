package com.cloudtestapi.common;

public abstract class AbstractRequestWithoutSpecificBodyGenerator extends AbstractRequest {

    @Override
    protected String toJsonBody() {
        return null;
    }
}

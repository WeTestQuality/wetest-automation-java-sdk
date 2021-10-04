package com.cloudtestapi.common;

/*
 * Copyright (c) 2018 THL A29 Limited, a Tencent company. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;

public abstract class AbstractRequest {

    private String path = "/";

    @Expose(serialize = false)
    @SerializedName("request_version")
    private String version = "v1";

    private String httpMethod = "POST";

    protected abstract String toJsonBody();

    protected abstract void toQueryParamMap(HashMap<String, Object> map, String prefix);

    protected abstract void toPathParamMap(HashMap<String, String> map, String prefix);

    protected void withApiInfo(String version, String path) {
        setPath(path);
        setVersion(version);
    }

    protected String getPath() {
        return this.path;
    }

    protected void setPath(String path) {
        this.path = path;
    }

    protected String getVersion() {
        return this.version;
    }

    protected void setVersion(String version) {
        this.version = version;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
}

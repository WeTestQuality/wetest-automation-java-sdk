package com.cloudtestapi.test;

import com.cloudtestapi.common.AbstractClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.test.models.CancelTestRequest;
import com.cloudtestapi.test.models.CompatibilityTest;
import com.cloudtestapi.test.models.FunctionalTest;
import com.cloudtestapi.test.models.StartCompatibilityTestRequest;
import com.cloudtestapi.test.models.StartFunctionalTestRequest;
import com.cloudtestapi.test.models.StartTestResponse;
import com.cloudtestapi.test.models.TestInfo;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class TestClient extends AbstractClient {
    public TestClient(Credential credential) {
        this(credential, new ClientProfile());
    }

    public TestClient(Credential credential, ClientProfile clientProfile) {
        super(credential, clientProfile);
    }

    /**
     * 开启兼容性测试
     * @param request StartCompatibilityTestRequest
     * @return  TestInfo
     * @throws CloudTestSDKException CloudTestSDKException
     */
    private TestInfo startCompatibilityTest(StartCompatibilityTestRequest request) throws CloudTestSDKException {
        StartTestResponse rsp = null;
        String rspStr = "";
        try{
            Type type = new TypeToken<StartTestResponse>(){}.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        }catch (JsonSyntaxException e){
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.testInfo;
    }

    /**
     * 开启功能测试
     * @param test FunctionalTest
     * @return TestInfo
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public TestInfo startFunctionalTest(FunctionalTest test) throws CloudTestSDKException{
        StartFunctionalTestRequest request = new StartFunctionalTestRequest();
        request.setTest(test);
        StartTestResponse rsp = null;
        String rspStr = "";
        try{
            Type type = new TypeToken<StartTestResponse>(){}.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        }catch (JsonSyntaxException e){
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.testInfo;
    }

    /**
     * 开启兼容性测试 StartCompatibilityTestRequest
     * @param test  CompatibilityTest
     * @return TestInfo
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public TestInfo startCompatibilityTest(CompatibilityTest test) throws CloudTestSDKException{
        StartCompatibilityTestRequest request = new StartCompatibilityTestRequest();
        request.setTest(test);
        return startCompatibilityTest(request);
    }

    /**
     * 取消测试
     * @param testId 测试ID
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public void cancelTest(Long testId) throws CloudTestSDKException{
        CancelTestRequest request = new CancelTestRequest();
        request.setTestId(testId);
        this.internalRequest(request);
    }
}

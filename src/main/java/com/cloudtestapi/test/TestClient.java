package com.cloudtestapi.test;

import com.cloudtestapi.common.AbstractClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.test.models.AutomationTest;
import com.cloudtestapi.test.models.CancelTestRequest;
import com.cloudtestapi.test.models.GetTestDevicesRequest;
import com.cloudtestapi.test.models.GetTestDevicesResponse;
import com.cloudtestapi.test.models.StartAutomationTestRequest;
import com.cloudtestapi.test.models.StartTestResponse;
import com.cloudtestapi.test.models.TestDevice;
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
     * Start automation test
     * @param test auto test parameters
     * @return TestInfo
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public TestInfo startAutomationTest(AutomationTest test) throws CloudTestSDKException{
        StartTestResponse rsp = null;
        String rspStr = "";
        StartAutomationTestRequest request = new StartAutomationTestRequest();
        request.setTest(test);
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
     * cancel test
     * @param testId test id
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public void cancelTest(Long testId) throws CloudTestSDKException{
        CancelTestRequest request = new CancelTestRequest();
        request.setTestId(testId);
        this.internalRequest(request);
    }

    /**
     * get test devices
     * @param testId test ID
     * @param log require log?
     * @param image require image?
     * @param error require error?
     * @return TestDevice[] device info list
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public TestDevice[] getTestDevices(Long testId, boolean log, boolean image, boolean error) throws CloudTestSDKException{
        GetTestDevicesRequest request = new GetTestDevicesRequest();
        request.setTestId(testId);
        request.setLog(log);
        request.setImage(image);
        request.setError(error);
        GetTestDevicesResponse rsp = null;
        String rspStr = "";
        try{
            Type type = new TypeToken<GetTestDevicesResponse>(){}.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        }catch (JsonSyntaxException e){
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.devices;

    }


}

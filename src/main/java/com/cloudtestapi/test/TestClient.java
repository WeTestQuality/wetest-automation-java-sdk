package com.cloudtestapi.test;

import com.cloudtestapi.common.AbstractClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.test.models.*;
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
     *
     * @param test auto test parameters
     * @return TestInfo
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public TestInfo startAutomationTest(AutomationTest test) throws CloudTestSDKException {
        StartTestResponse rsp = null;
        String rspStr = "";
        StartAutomationTestRequest request = new StartAutomationTestRequest();
        request.setTest(test);
        try {
            Type type = new TypeToken<StartTestResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.testInfo;
    }

    public DebugTestInfo startDebugTest(Integer deviceId, String projectKey) throws CloudTestSDKException {
        StartDebugTestResponse rsp = null;
        String rspStr = "";
        StartDebugTestRequest request = new StartDebugTestRequest()
                .setDeviceId(deviceId)
                .setProject(projectKey);
        try {
            Type type = new TypeToken<StartDebugTestResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.debugTestInfo;
    }

    public StartDebugConnectResponse startRemoteDebugConnect(Long testId, int deviceId) throws CloudTestSDKException {
        StartDebugConnectResponse rsp = null;
        String rspStr = "";
        StartDebugConnectRequest request = new StartDebugConnectRequest();
        request.setTestId(testId);
        request.setDeviceId(deviceId);
        try {
            Type type = new TypeToken<StartDebugConnectResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp;
    }

    /**
     * cancel test
     *
     * @param testId test id
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public void cancelTest(Long testId) throws CloudTestSDKException {
        CancelTestRequest request = new CancelTestRequest();
        request.setTestId(testId);
        this.internalRequest(request);
    }

    /**
     * get test devices
     *
     * @param testId test ID
     * @param log require log?
     * @param image require image?
     * @param error require error?
     * @return TestDevice[] device info list
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public TestDevice[] getTestDevices(Long testId, boolean log, boolean image, boolean error)
            throws CloudTestSDKException {
        GetTestDevicesRequest request = new GetTestDevicesRequest();
        request.setTestId(testId);
        request.setLog(log);
        request.setImage(image);
        request.setError(error);
        GetTestDevicesResponse rsp = null;
        String rspStr = "";
        try {
            Type type = new TypeToken<GetTestDevicesResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.devices;

    }

    /**
     * 获取测试状态
     *
     * @param testId 测试ID
     * @return TestStatus 测试状态
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public TestStatus getTestStatus(Long testId) throws CloudTestSDKException {
        GetTestStatusRequest request = new GetTestStatusRequest();
        request.setTestId(testId);
        GetTestStatusResponse rsp = null;
        String rspStr = "";
        try {
            Type type = new TypeToken<GetTestStatusResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.testStatus;
    }


    public GetDebugStatusResponse getDebugStatus(Long testId, Integer deviceId) throws CloudTestSDKException {
        GetDebugStatusRequest request = new GetDebugStatusRequest();
        request.setTestId(testId);
        request.setDeviceId(deviceId);
        GetDebugStatusResponse rsp = null;
        String rspStr = "";
        try {
            Type type = new TypeToken<GetDebugStatusResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp;
    }

    public InstallAppResponse installApp(Long testId, Integer deviceId, Integer appId, Boolean resign) throws CloudTestSDKException {
        InstallAppRequest request = new InstallAppRequest();
        request.setTestId(testId);
        request.setDeviceId(deviceId);
        request.setAppId(appId);
        request.setResign(resign);
        InstallAppResponse rsp = null;
        String rspStr = "";
        try {
            Type type = new TypeToken<InstallAppResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp;
    }

    public GetInstallProcessResponse getInstallProcess(Long testId, Integer deviceId, Integer installId)
            throws CloudTestSDKException {
        GetInstallProcessRequest request = new GetInstallProcessRequest();
        request.setTestId(testId);
        request.setDeviceId(deviceId);
        request.setInstallId(installId);
        GetInstallProcessResponse rsp = null;
        String rspStr = "";
        try {
            Type type = new TypeToken<GetInstallProcessResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp;
    }

}

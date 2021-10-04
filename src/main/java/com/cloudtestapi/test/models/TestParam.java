package com.cloudtestapi.test.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestParam {
    @Expose
    public String[][] accounts;

    @Expose
    @SerializedName("app_id")
    public int appId;

    @Expose
    @SerializedName("cloud_ids")
    public int[] cloudIds;

    @Expose
    @SerializedName("device_number")
    public int deviceNumber;

    @Expose
    @SerializedName("min_device_number")
    public int minDeviceNumber;

    @Expose
    public int[] devices;

    @Expose
    @SerializedName("extra_scripts")
    public int[] extraScripts;

    @Expose
    @SerializedName("extrainfo")
    public String extraInfo;

    @Expose
    @SerializedName("frame_type")
    public String frameType;

    @Expose
    @SerializedName("has_video")
    public boolean hasVideo;

    @Expose
    @SerializedName("login")
    public String login;

    @Expose
    @SerializedName("max_test_runtime")
    public int maxTestRunTime;

    @Expose
    @SerializedName("preinstall_apps")
    public int[] preinstallApps;

    @Expose
    @SerializedName("script_id")
    public int scriptId;

    @Expose
    @SerializedName("callback_url")
    public String callbackUrl;

    @Expose
    public int priority;

    @Expose
    public String project;

    @Expose
    @SerializedName("device_groups")
    public String[] deviceGroups;

    @Expose
    @SerializedName("device_choose_type")
    public String deviceChooseType;

    @Expose
    @SerializedName("order_account_type")
    public String orderAccountType;


    public String[][] getAccounts() {
        return accounts;
    }

    public void setAccount(String[][] accounts) {
        this.accounts = accounts;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int[] getCloudIds() {
        return cloudIds;
    }

    public void setCloudIds(int[] cloudIds) {
        this.cloudIds = cloudIds;
    }

    public int getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(int deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public int getMinDeviceNumber() {
        return minDeviceNumber;
    }

    public void setMinDeviceNumber(int minDeviceNumber) {
        this.minDeviceNumber = minDeviceNumber;
    }

    public int[] getDevices() {
        return devices;
    }

    public void setDevices(int[] devices) {
        this.devices = devices;
    }

    public int[] getExtraScripts() {
        return extraScripts;
    }

    public void setExtraScripts(int[] extraScripts) {
        this.extraScripts = extraScripts;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getFrameType() {
        return frameType;
    }

    public void setFrameType(String frameType) {
        this.frameType = frameType;
    }

    public boolean isHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getMaxTestRunTime() {
        return maxTestRunTime;
    }

    public void setMaxTestRunTime(int maxTestRunTime) {
        this.maxTestRunTime = maxTestRunTime;
    }

    public int[] getPreinstallApps() {
        return preinstallApps;
    }

    public void setPreinstallApps(int[] preinstallApps) {
        this.preinstallApps = preinstallApps;
    }

    public int getScriptId() {
        return scriptId;
    }

    public void setScriptId(int scriptId) {
        this.scriptId = scriptId;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String[] getDeviceGroups() {
        return deviceGroups;
    }

    public void setDeviceGroups(String[] deviceGroups) {
        this.deviceGroups = deviceGroups;
    }

    public String getDeviceChooseType() {
        return deviceChooseType;
    }

    public void setDeviceChooseType(String deviceChooseType) {
        this.deviceChooseType = deviceChooseType;
    }

    public String getOrderAccountType() {
        return orderAccountType;
    }

    public void setOrderAccountType(String orderAccountType) {
        this.orderAccountType = orderAccountType;
    }
}

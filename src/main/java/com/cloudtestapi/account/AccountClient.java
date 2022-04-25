package com.cloudtestapi.account;

import com.cloudtestapi.account.models.*;
import com.cloudtestapi.common.AbstractClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class AccountClient extends AbstractClient {

    public AccountClient(Credential credential) {
        this(credential, new ClientProfile());
    }

    public AccountClient(Credential credential,
            ClientProfile clientProfile) {
        super(credential, clientProfile);
    }

    /**
     * Get Project List
     *
     * @return Project List
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public Project[] getProjects() throws CloudTestSDKException {
        GetUserProjectRequest request = new GetUserProjectRequest();
        GetUserProjectResponse rsp;
        String rspStr = "";
        try {
            Type type = new TypeToken<GetUserProjectResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.projects;
    }

    public Project getProjectByName(String projectName) throws CloudTestSDKException {
        Project[] projects = this.getProjects();
        for (Project p:
                projects
        ) {
            if (p.projectName.equals(projectName)) {
                return p;
            }
        }
        return null;
    }

    public Cloud[] getClouds() throws CloudTestSDKException {
        GetUserCloudsRequest request = new GetUserCloudsRequest();
        GetUserCloudsResponse rsp;
        String rspStr = "";
        try {
            Type type = new TypeToken<GetUserCloudsResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.clouds;
    }

    public Cloud getCloudByName(String cloudName) throws CloudTestSDKException {
        Cloud[] clouds = this.getClouds();
        for (Cloud c:
                clouds
             ) {
            if (c.cloudName.equals(cloudName)) {
                return c;
            }
        }
        return null;
    }
}

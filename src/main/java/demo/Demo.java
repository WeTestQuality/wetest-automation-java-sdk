package demo;

import com.cloudtestapi.CTClient;
import com.cloudtestapi.account.models.Cloud;
import com.cloudtestapi.account.models.Project;
import com.cloudtestapi.common.Constants;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.common.profile.HttpProfile;
import com.cloudtestapi.device.models.Device;
import com.cloudtestapi.device.models.GetDevicesResponse;
import com.cloudtestapi.test.models.DebugTestInfo;
import com.cloudtestapi.test.models.GetInstallProcessResponse;
import com.cloudtestapi.test.models.InstallAppResponse;
import com.cloudtestapi.test.models.StartDebugConnectResponse;
import com.cloudtestapi.upload.models.App;

public class Demo {


    public static Device chooseFreeDevice(Device[] devices) {
        for (Device d :
                devices) {
            if (d.deviceStateCode == Constants.DeviceState.FREE) {
                return d;
            }
        }
        return null;
    }


    public static void testAndroid(CTClient client) throws Exception {
        // 1.1 »ñÈ¡ÓÃ»§ÏîÄ¿ÁÐ±í
        Project[] projects = client.account.getProjects();
        System.out.println(client.gson.toJson(projects));
        // 1.2 Í¨¹ýÏîÄ¿Ãû³Æ»ñÈ¡ÏîÄ¿ÐÅÏ¢
        Project project = client.account.getProjectByName("uftm");
        System.out.println(client.gson.toJson(project));
        // 2.1 »ñÈ¡ÓÃ»§ÓµÓÐÈ¨ÏÞµÄÔÆ
        Cloud[] clouds = client.account.getClouds();
        System.out.println(client.gson.toJson(clouds));
        // 2.2 Í¨¹ýÔÆÃû³Æ»ñÈ¡ÔÆÐÅÏ¢
        Cloud cloud = client.account.getCloudByName("UFT×¨ÓÐÔÆ");
        System.out.println(client.gson.toJson(cloud));
        // 3. »ñÈ¡Ö¸¶¨ÔÆÈ¨ÏÞÏÂµÄÉè±¸ÁÐ±í
        GetDevicesResponse devicesResp = client.device.getDevicesByCloudId(cloud.cloudId);
        System.out.println(client.gson.toJson(devicesResp.devices));
        // 4. É¸Ñ¡Ò»¸ö¿ÕÏÐÉè±¸
        Device freeDevice = chooseFreeDevice(devicesResp.devices);
        System.out.println("Ñ¡Ôñ¿ÕÏÐÉè±¸ " + client.gson.toJson(freeDevice));
        // 5. ¿ªÆôÔ¶³Ìµ÷ÊÔ£¬Õ¼ÓÃÉè±¸
        DebugTestInfo testInfo = client.test.startDebugTest(freeDevice.deviceID, project.keyName);
        System.out.println(client.gson.toJson(testInfo));
        // 6. ¿ªÆô wdb Ô¶³Ìµ÷ÊÔ &&  7. »ñÈ¡ wdb Á¬½ÓµØÖ·ºÍÃØÔ¿ , ºÏ²¢
        StartDebugConnectResponse connectResponse = client.test.startRemoteDebugConnect(testInfo.testId, freeDevice.deviceID);
        System.out.println(client.gson.toJson(connectResponse));
        // 8. Ö´ÐÐ±¾µØwdb connect µØÖ·ºÍÃÜÂë
        // FIXME: ´ýÖÕ¶ËÊµÏÖ
        client.device.wdbConnect(connectResponse.debugStatus.addr, connectResponse.debugStatus.token, 10);
        // 9. ÉÏ´« & °²×°apk & ÂÖÑ¯°²×°×´Ì¬
        App androidDemo =  client.upload.uploadApp("test.apk");
        System.out.println(client.gson.toJson(androidDemo));
        InstallAppResponse install = client.test.installApp(testInfo.testId, freeDevice.deviceID, androidDemo.appId, true);
        System.out.println(client.gson.toJson(install));
        while (true) {
            GetInstallProcessResponse resp = client.test.getInstallProcess(testInfo.testId,
                    freeDevice.deviceID, install.installInfo.install_id);
            System.out.println(client.gson.toJson(resp));
            if(resp.installInfo.progress == Constants.InstallProgress.DOWNLOADING) {
                System.out.println("ÏÂÔØÖÐ.....");
            } else if(resp.installInfo.progress == Constants.InstallProgress.PUSHING) {
                System.out.println("ÍÆËÍÖÐ.....");
            } else if(resp.installInfo.progress == Constants.InstallProgress.INSTALLING) {
                System.out.println("°²×°ÖÐ.....");
            }
            if(resp.installInfo.result > 100) {
                System.out.println("°²×°Ê§°Ü£º" + resp.installInfo.msg);
                break;
            } else if(resp.installInfo.result == 100) {
                System.out.println("°²×°Íê³É");
                break;
            }  else {
                System.out.println(String.format("°²×°½ø¶È %d", resp.installInfo.result));
            }
            Thread.sleep(2000);
        }
        // 9. 5Ãëºó£¬ÊÍ·ÅÉè±¸
        Thread.sleep(5000);
        System.out.println("----ÊÍ·ÅÉè±¸-----");
//        client.test.cancelTest(testInfo.testId);
    }


    public static void main(String[] args) throws  Exception {
        String secretId = "e6d138fb86714c0cb32ad94e9f7abbbe";
        String secretKey = "c96jpicn01bm1h6eblq0";
        // ³õÊ¼»¯¿Í»§¶Ëµ÷ÓÃÅäÖÃ
        CTClient client = new CTClient(
                new Credential(secretId,
                        secretKey),
                new ClientProfile(ClientProfile.SIGN_SHA256,
                        new HttpProfile()
                                .setRootDomain("api.paas.wetest.qq.com")
                                .setToolPath("cloudtest")
                                .setProtocol(HttpProfile.REQ_HTTPS))
        );
        System.out.println(client.gson.toJson(client.slot.slotListInfo("")));
    }
}

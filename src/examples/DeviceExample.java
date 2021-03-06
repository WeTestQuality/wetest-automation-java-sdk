import com.cloudtestapi.CTClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.common.profile.HttpProfile;
import com.cloudtestapi.device.models.DeviceBasicInfo;
import com.cloudtestapi.device.models.GetDevicesResponse;
import com.cloudtestapi.device.models.ModelList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeviceExample {

    private CTClient client;

    @BeforeEach
    void init() throws CloudTestSDKException {
        // Instantiate http option
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setRootDomain(System.getenv("CT_API_DOMAIN"));
        httpProfile.setToolPath("cloudtest");
        httpProfile.setProtocol(HttpProfile.REQ_HTTP);

        // Instantiate client profile, for now, only SIGN_SHA256 signature supported
        ClientProfile profile = new ClientProfile(ClientProfile.SIGN_SHA256, httpProfile);

        // Init credential
        Credential cred = new Credential(System.getenv("CT_SECRET_ID"),
                System.getenv("CT_SECRET_KEY"));

        // Instantiate client
        this.client = new CTClient(cred, profile);

        // set logger
        // this.client.setLogger();
    }

    @Test
    void test_get_devices_by_cloud_id() throws CloudTestSDKException {
        Assertions.assertDoesNotThrow(() -> {
            GetDevicesResponse response = this.client.device.getDevicesByCloudId(1);
            System.out.println(this.client.gson.toJson(response));
        });
    }

    @Test
    void test_get_device_state() throws CloudTestSDKException {
        Assertions.assertDoesNotThrow(() -> {
            DeviceBasicInfo deviceInfo = this.client.device.getDeviceState(6, 1);
            System.out.println(this.client.gson.toJson(deviceInfo));
        });
    }

    @Test
    void test_get_model_list() throws CloudTestSDKException {
        Assertions.assertDoesNotThrow(() -> {
            ModelList[] modelList = this.client.device.getModelList(System.getenv("PROJECT_ID"));
            System.out.println(this.client.gson.toJson(modelList));
        });
    }
}

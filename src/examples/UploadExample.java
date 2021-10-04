import com.cloudtestapi.CTClient;

import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.common.profile.HttpProfile;
import com.cloudtestapi.upload.models.App;
import com.cloudtestapi.upload.models.GetAppInfoRequest;
import com.cloudtestapi.upload.models.GetScriptInfoRequest;
import com.cloudtestapi.upload.models.Script;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UploadExample{
    private CTClient client;

    @BeforeEach
    void init() throws CloudTestSDKException{
        Credential cred = new Credential(System.getenv("CT_SECRET_ID"),
                System.getenv("CT_SECRET_KEY"));
        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setRootDomain(System.getenv("CT_API_DOMAIN"));
        httpProfile.setToolPath("cloudtest");
        httpProfile.setProtocol(HttpProfile.REQ_HTTP);

        // 实例化一个client选项，可选的，没有特殊需求可跳过
        ClientProfile profile = new ClientProfile(ClientProfile.SIGN_SHA256, httpProfile);
//        profile.setDebug(true);

        // 实例化client
        this.client = new CTClient(cred, profile);
    }

    @Test
    void test_get_app_info(){
        Assertions.assertDoesNotThrow(() -> {
            App app = this.client.upload.getAppInfo(384);
            System.out.println(this.client.gson.toJson(app));
        });
    }

    @Test
    void test_get_script_info(){
        Assertions.assertDoesNotThrow(() -> {
            Script script = this.client.upload.getScriptInfo(1420);
            System.out.println(this.client.gson.toJson(script));
        });
    }

}

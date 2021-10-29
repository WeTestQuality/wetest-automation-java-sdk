import com.cloudtestapi.CTClient;

import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.common.profile.HttpProfile;
import com.cloudtestapi.upload.models.App;
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
        // Instantiate http option
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setRootDomain(System.getenv("CT_API_DOMAIN"));
        httpProfile.setToolPath("cloudtest");
        httpProfile.setProtocol(HttpProfile.REQ_HTTP);

        // Instantiate client profile, for now, only SIGN_SHA256 signature supported
        ClientProfile profile = new ClientProfile(ClientProfile.SIGN_SHA256, httpProfile);

        // set logger
        this.client = new CTClient(cred, profile);
    }

    @Test
    void test_get_app_info(){
        Assertions.assertDoesNotThrow(() -> {
            // replace the number to your app id
            App app = this.client.upload.getAppInfo(1);
            System.out.println(this.client.gson.toJson(app));
        });
    }

    @Test
    void test_get_script_info(){
        Assertions.assertDoesNotThrow(() -> {
            // replace the number to your script id
            Script script = this.client.upload.getScriptInfo(1);
            System.out.println(this.client.gson.toJson(script));
        });
    }

    @Test
    void test_upload_apk(){
        Assertions.assertDoesNotThrow(() -> {
            App app = this.client.upload.multiPartUploadApk("YOUR_APK_FILE_PATH");
            System.out.println(this.client.gson.toJson(app));
        });
    }

    @Test
    void test_upload_ipa(){
        Assertions.assertDoesNotThrow(() -> {
            App app = this.client.upload.multiPartUploadIpa("YOUR_IPA_FILE_PATH");
            System.out.println(this.client.gson.toJson(app));
        });
    }

    @Test
    void test_upload_script(){
        Assertions.assertDoesNotThrow(() -> {
            Script script = this.client.upload.multiPartUploadScript("YOUR_SCRIPT_FILE_PATH");
            System.out.println(this.client.gson.toJson(script));
        });
    }

}

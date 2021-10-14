import com.cloudtestapi.CTClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.common.profile.HttpProfile;
import com.cloudtestapi.slot.models.ModelInfo;
import com.cloudtestapi.slot.models.ResignResult;
import com.cloudtestapi.slot.models.SlotInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SlotExample {
    private CTClient client;

    @BeforeEach
    void init() throws CloudTestSDKException {
        // specify credential
        Credential cred = new Credential(System.getenv("CT_SECRET_ID"),
                System.getenv("CT_SECRET_KEY"));
        // specify http profile
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setRootDomain(System.getenv("CT_API_DOMAIN"));
        httpProfile.setToolPath("cloudtest");
        httpProfile.setProtocol(HttpProfile.REQ_HTTPS);

        // specify client profile
        ClientProfile profile = new ClientProfile(ClientProfile.SIGN_SHA256, httpProfile);

        // new client
        this.client = new CTClient(cred, profile);
    }

    @Test
    void test_slot_list_info(){
        Assertions.assertDoesNotThrow(() -> {
            SlotInfo[] list = this.client.slot.slotListInfo("42.194.235.34:20001");
            System.out.println(this.client.gson.toJson(list));
        });
    }

    @Test
    void test_slot_list_model(){
        Assertions.assertDoesNotThrow(() -> {
            ModelInfo[] list = this.client.slot.slotListModel(new int[]{0, 1, 2,3});
            System.out.println(this.client.gson.toJson(list));
        });
    }

    @Test
    void test_slot_switch_start(){
        Assertions.assertDoesNotThrow(() -> {
            this.client.slot.slotSwitchStart("42.194.235.34:20001", 274);
        });
    }

    @Test
    void test_slot_switch_cancel(){
        Assertions.assertDoesNotThrow(() -> {
            this.client.slot.slotSwitchCancel("42.194.235.34:20001");
        });
    }

    @Test
    void test_resign_ipa(){
        Assertions.assertDoesNotThrow(() -> {
            String fileId = this.client.slot.resignIpa("119.29.118.154:20004",
                    "https://files.wetest.qq.com/files/wetest-file/b877c7c315ec4d4bb0df41d0c017238a.ipa?nonce=3530759385&t=1634109139&token=4b951fdd6845290157e455f835bfa3cb8434859edfeb8e988ed51af140feff0d&zone_id=1");
            System.out.println(fileId);
        });
    }

    @Test
    void test_fetch_resign_result(){
        Assertions.assertDoesNotThrow(() -> {
            ResignResult result = this.client.slot.fetchResignResult("119.29.118.154:20004", "20211013161542-c5j9bbjum956gta7e9o0");
            System.out.println(this.client.gson.toJson(result));
        });
    }


}

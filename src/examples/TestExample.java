import com.cloudtestapi.CTClient;
import com.cloudtestapi.common.Constants.ModelListFilterType;
import com.cloudtestapi.common.Constants.OrderAccountType;
import com.cloudtestapi.common.Constants.ParserType;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.common.profile.HttpProfile;
import com.cloudtestapi.test.models.AutomationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestExample {
    private CTClient client;

    @BeforeEach
    void init() throws CloudTestSDKException {
        Credential cred = new Credential(System.getenv("CT_SECRET_ID"),
                System.getenv("CT_SECRET_KEY"));
        // Instantiate http option
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setRootDomain(System.getenv("CT_API_DOMAIN"));
        httpProfile.setToolPath("cloudtest");
        httpProfile.setProtocol(HttpProfile.REQ_HTTP);

        // Instantiate client profile, for now, only SIGN_SHA256 signature supported
        ClientProfile profile = new ClientProfile(ClientProfile.SIGN_SHA256, httpProfile);

        // Instantiate client
        this.client = new CTClient(cred, profile);

        // set logger
        // this.client.setLogger();
    }

    @Test
    void test_start_automation_test() throws CloudTestSDKException{
        AutomationTest test = new AutomationTest();
        test.setAccount(new String[][]{new String[]{"n1", "p1"}});
        test.setAppId(384);
        test.setCloudIds(new int[]{1});
        test.setDeviceNumber(0);
        test.setDevices(new int[]{6});
        test.setExtraScripts(null);
        test.setExtraInfo("fail");
        test.setFrameType("wechat_init");
        test.setHasVideo(false);
        test.setLogin("customized");
        test.setMaxTestRunTime(600);
        test.setPreinstallApps(null);
        test.setScriptId(1420);
        test.setMaxCaseRuntime(600);
        test.setParserType(ParserType.PyTest.getValue());
    }
    @Test
    void test_cancel_test() throws CloudTestSDKException{
        this.client.test.cancelTest(2021100400171951L);
    }

    @Test
    void test_logger(){
        this.client.getLogger().info("hello world");
    }

    @Test
    void test_constants(){
        System.out.println(ModelListFilterType.MODEL.getCode());
        System.out.println(ModelListFilterType.DEVICE.getCode());

        System.out.println(OrderAccountType.PERSON.getValue());

        System.out.println(ParserType.Custom.getValue());
        System.out.println(ParserType.PyTest.getValue());
    }
}

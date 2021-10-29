import com.cloudtestapi.CTClient;
import com.cloudtestapi.account.models.Project;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.common.profile.HttpProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountExample {
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
    }


    @Test
    void test_get_projects() throws CloudTestSDKException{
        Assertions.assertDoesNotThrow(() -> {
            Project[] projects = this.client.account.getProjects();
            System.out.println(this.client.gson.toJson(projects));
        });
    }
}

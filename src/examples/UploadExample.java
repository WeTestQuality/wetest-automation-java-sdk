import com.cloudtestapi.CTClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.common.profile.HttpProfile;
import com.cloudtestapi.upload.models.App;
import com.cloudtestapi.upload.models.Script;
import com.cloudtestapi.upload.models.WTApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UploadExample {

    private CTClient client;

    @BeforeEach
    void init() throws CloudTestSDKException {
        Credential cred = new Credential(System.getenv("CT_SECRET_ID"),
                System.getenv("CT_SECRET_KEY"));
        // 实例化一个http选项，可选的，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setRootDomain(System.getenv("CT_API_DOMAIN"));
        httpProfile.setToolPath("cloudtest");
        httpProfile.setProtocol(HttpProfile.REQ_HTTPS);

        // 实例化一个client选项，可选的，没有特殊需求可跳过
        ClientProfile profile = new ClientProfile(ClientProfile.SIGN_SHA256, httpProfile);
//        profile.setDebug(true);

        // 实例化client
        this.client = new CTClient(cred, profile);
    }

    @Test
    void test_get_app_info() {
        Assertions.assertDoesNotThrow(() -> {
            App app = this.client.upload.getAppInfo(384);
            System.out.println(this.client.gson.toJson(app));
        });
    }

    @Test
    void test_get_script_info() {
        Assertions.assertDoesNotThrow(() -> {
            Script script = this.client.upload.getScriptInfo(1420);
            System.out.println(this.client.gson.toJson(script));
        });
    }

    @Test
    void test_upload_apk() {
        Assertions.assertDoesNotThrow(() -> {
            App app = this.client.upload
                    .multiPartUploadApk("C:\\Users\\dafenghuang\\Downloads\\com.taotao.tuoping_1.5_7.apk");
            System.out.println(this.client.gson.toJson(app));
        });
    }

    @Test
    void test_upload_apk_wetest() {
        Assertions.assertDoesNotThrow(() -> {
            WTApp app = this.client.upload.multiPartUploadApkToWT(
                    "C:\\Users\\dafenghuang\\Downloads\\com.taotao.tuoping_1.5_7.apk", "fzb-8459"
            );
            System.out.println(this.client.gson.toJson(app));
        });
    }

    @Test
    void test_upload_ipa_wetest() {
        Assertions.assertDoesNotThrow(() -> {
            WTApp app = this.client.upload.multiPartUploadIpaToWT(
                    "C:\\Users\\dafenghuang\\Downloads\\test.ipa", "fzb-8459"
            );
            System.out.println(this.client.gson.toJson(app));
        });
    }

    @Test
    void test_upload_ipa() {
        Assertions.assertDoesNotThrow(() -> {
            App app = this.client.upload.multiPartUploadIpa("C:\\Users\\dafenghuang\\Downloads\\test.ipa");
            System.out.println(this.client.gson.toJson(app));
        });
    }

    @Test
    void test_upload_script() {
        Assertions.assertDoesNotThrow(() -> {
            Script script = this.client.upload.multiPartUploadScript("C:\\Users\\dafenghuang\\Downloads\\demo.zip");
            System.out.println(this.client.gson.toJson(script));
        });
    }

//    @Test
//    void test_get_wt_upload_info() {
//        Assertions.assertDoesNotThrow(() -> {
//            WTUploadInfo uploadInfo = this.client.upload
//                    .getWTUploadInfo("C:\\Users\\dafenghuang\\Downloads\\com.taotao.tuoping_1.5_7.apk",
//                            "fzb-8459");
//            System.out.println(this.client.gson.toJson(uploadInfo));
//        });
//    }

//    @Test
//    void test_get_upload_id(){
//        Assertions.assertDoesNotThrow(() ->{
//            UploadInfo uploadInfo = this.client.upload.getUploadInfo(
//                    "C:\\Users\\dafenghuang\\Downloads\\com.taotao.tuoping_1.5_7.apk", "android");
//            System.out.println(this.client.gson.toJson(uploadInfo));
//        });
//    }

//    @Test
//    void test_upload_chunk(){
//        Assertions.assertDoesNotThrow( () ->{
//            UploadInfo uploadInfo = this.client.upload.getUploadInfo(
//                    "C:\\Users\\dafenghuang\\Downloads\\com.taotao.tuoping_1.5_7.apk", "android");
//            System.out.println(this.client.gson.toJson(uploadInfo));
//
//            this.client.upload.uploadChunk("C:\\Users\\dafenghuang\\Downloads\\com.taotao.tuoping_1.5_7.apk",
//                    uploadInfo);
//        });
//    }

//    @Test
//    void test_upload_chunk_wt() {
//        Assertions.assertDoesNotThrow(() -> {
//            WTUploadInfo uploadInfo = this.client.upload.getWTUploadInfo(
//                    "C:\\Users\\dafenghuang\\Downloads\\com.taotao.tuoping_1.5_7.apk", "fzb-8459");
//            System.out.println(this.client.gson.toJson(uploadInfo));
//
//            this.client.upload.uploadChunkWT(uploadInfo);
//        });
//    }

//    @Test
//    void test_get_upload_result() {
//        Assertions.assertDoesNotThrow(() -> {
//            WTUploadInfo uploadInfo = this.client.upload.getWTUploadInfo(
//                    "C:\\Users\\dafenghuang\\Downloads\\com.taotao.tuoping_1.5_7.apk", "fzb-8459");
//            System.out.println(this.client.gson.toJson(uploadInfo));
//            this.client.upload.uploadChunkWT(uploadInfo);
//            for (int i = 0; i < 10; i++) {
//                WTUploadResult result = this.client.upload.getWTUploadResult(uploadInfo);
//                System.out.println(this.client.gson.toJson(result));
//                Thread.sleep(1000);
//            }
//        });
//    }

//    @Test
//    void test_dump_apk(){
//        Assertions.assertDoesNotThrow( () -> {
//            UploadInfo uploadInfo = this.client.upload.getUploadInfo(
//                    "C:\\Users\\dafenghuang\\Downloads\\com.taotao.tuoping_1.5_7.apk", "android");
//            System.out.println(this.client.gson.toJson(uploadInfo));
//
//            this.client.upload.uploadChunk("C:\\Users\\dafenghuang\\Downloads\\com.taotao.tuoping_1.5_7.apk",
//                    uploadInfo);
//
//            App app = this.client.upload.dumpApk("C:\\Users\\dafenghuang\\Downloads\\com.taotao.tuoping_1.5_7.apk", uploadInfo);
//            System.out.println(this.client.gson.toJson(app));
//        });
//    }

//    @Test
//    void test_dump_apk_in_WT() {
//        Assertions.assertDoesNotThrow(() -> {
//            WTUploadInfo uploadInfo = this.client.upload.getWTUploadInfo(
//                    "C:\\Users\\dafenghuang\\Downloads\\com.taotao.tuoping_1.5_7.apk", "fzb-8459");
//            System.out.println(this.client.gson.toJson(uploadInfo));
//
//            WTApp app = this.client.upload.dumpApkInWT(uploadInfo);
//            System.out.println(this.client.gson.toJson(app));
//        });
//    }

//    @Test
//    void test_dump_ipa(){
//        Assertions.assertDoesNotThrow( () -> {
//            UploadInfo uploadInfo = this.client.upload.getUploadInfo(
//                    "C:\\Users\\dafenghuang\\Downloads\\test.ipa", "ios");
//            System.out.println(this.client.gson.toJson(uploadInfo));
//
//            this.client.upload.uploadChunk("C:\\Users\\dafenghuang\\Downloads\\test.ipa",
//                    uploadInfo);
//
//            App app = this.client.upload.dumpIPA("C:\\Users\\dafenghuang\\Downloads\\test.ipa", uploadInfo);
//            System.out.println(this.client.gson.toJson(app));
//        });
//    }

}

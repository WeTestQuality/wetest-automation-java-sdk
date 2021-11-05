# WeTest Automation Java SDK
## Prepare
Java 8 + 

## Credential
- Get a pair of secret id and secret key from [WeTest](https://console.wetest.net/account/base)
- See: Account Settings -> Developer Account 

## Demo
- current CT_API_DOMAIN=api.tool.wetest.net
```
import com.cloudtestapi.CTClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.common.profile.HttpProfile;
import com.cloudtestapi.device.models.GetDevicesResponse;

// Instantiate http option
HttpProfile httpProfile = new HttpProfile();
httpProfile.setRootDomain(System.getenv("CT_API_DOMAIN"));
httpProfile.setToolPath("cloudtest");
httpProfile.setProtocol(HttpProfile.REQ_HTTPS);

// Instantiate client profile, for now, only SIGN_SHA256 signature supported
ClientProfile profile = new ClientProfile(ClientProfile.SIGN_SHA256, httpProfile);

// Init credential
Credential cred = new Credential(System.getenv("CT_SECRET_ID"),
        System.getenv("CT_SECRET_KEY"));

// Instantiate client
this.client = new CTClient(cred, profile);

// set logger
// this.client.setLogger();


// start request, for example, get devices from a cloud by cloudid
// invalid access or other problems may trigger CloudTestSDKException
try{
    GetDevicesResponse response = this.client.device.getDevicesByCloudId(1);
    System.out.println(this.client.gson.toJson(response));
}catch (CloudTestSDKException e){
    e.printStackTrace();
    // you can also get message, errorcode and traceId from the exception
}



```
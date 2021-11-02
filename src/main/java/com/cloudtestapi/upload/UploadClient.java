package com.cloudtestapi.upload;

import com.cloudtestapi.common.AbstractClient;
import com.cloudtestapi.common.Credential;
import com.cloudtestapi.common.JsonResponseModel;
import com.cloudtestapi.common.exception.CloudTestSDKException;
import com.cloudtestapi.common.profile.ClientProfile;
import com.cloudtestapi.upload.models.App;
import com.cloudtestapi.upload.models.DumpAppRequest;
import com.cloudtestapi.upload.models.DumpAppResponse;
import com.cloudtestapi.upload.models.DumpAppWTRequest;
import com.cloudtestapi.upload.models.DumpScriptRequest;
import com.cloudtestapi.upload.models.DumpScriptResponse;
import com.cloudtestapi.upload.models.GetAppInfoRequest;
import com.cloudtestapi.upload.models.GetAppInfoResponse;
import com.cloudtestapi.upload.models.GetScriptInfoRequest;
import com.cloudtestapi.upload.models.GetScriptInfoResponse;
import com.cloudtestapi.upload.models.GetUploadFileIdRequest;
import com.cloudtestapi.upload.models.GetUploadFileIdResponse;
import com.cloudtestapi.upload.models.GetUploadIdFromWTRequest;
import com.cloudtestapi.upload.models.GetUploadIdFromWTResponse;
import com.cloudtestapi.upload.models.GetUploadResultFromWTRequest;
import com.cloudtestapi.upload.models.GetUploadResultFromWTResponse;
import com.cloudtestapi.upload.models.Script;
import com.cloudtestapi.upload.models.UploadInfo;
import com.cloudtestapi.upload.models.UploadRequest;
import com.cloudtestapi.upload.models.UploadWTRequest;
import com.cloudtestapi.upload.models.WTApp;
import com.cloudtestapi.upload.models.WTUploadInfo;
import com.cloudtestapi.upload.models.WTUploadResult;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


public class UploadClient extends AbstractClient {

    public UploadClient(Credential credential) {
        this(credential, new ClientProfile());
    }

    public UploadClient(Credential credential, ClientProfile clientProfile) {
        super(credential, clientProfile);
    }

    /**
     * Get App info
     *
     * @param appId int
     * @return App
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public App getAppInfo(int appId) throws CloudTestSDKException {
        GetAppInfoRequest request = new GetAppInfoRequest();
        request.setAppId(appId);
        GetAppInfoResponse rsp = null;
        String rspStr = "";
        try {
            Type type = new TypeToken<GetAppInfoResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.app;
    }

    /**
     * Get script info
     *
     * @param request GetScriptInfoRequest
     * @return Script
     * @throws CloudTestSDKException CloudTestSDKException
     */
    private Script getScriptInfo(GetScriptInfoRequest request) throws CloudTestSDKException {
        GetScriptInfoResponse rsp = null;
        String rspStr = "";
        try {
            Type type = new TypeToken<GetScriptInfoResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.script;
    }

    /**
     * Get script info
     *
     * @param scriptId int
     * @return Script
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public Script getScriptInfo(int scriptId) throws CloudTestSDKException {
        GetScriptInfoRequest request = new GetScriptInfoRequest();
        request.setScriptId(scriptId);
        return this.getScriptInfo(request);
    }

    /**
     * Upload apk in multi part
     *
     * @param filePath file path
     * @return App App info
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public App multiPartUploadApk(String filePath) throws CloudTestSDKException {
        UploadInfo uploadInfo = this.getUploadInfo(filePath, "android");
        this.uploadChunk(filePath, uploadInfo);
        return this.dumpApk(filePath, uploadInfo);
    }

    /**
     * Upload ipa file in multi part
     *
     * @param filePath file path
     * @return App app info
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public App multiPartUploadIpa(String filePath) throws CloudTestSDKException {
        UploadInfo uploadInfo = this.getUploadInfo(filePath, "ios");
        this.uploadChunk(filePath, uploadInfo);
        return this.dumpIPA(filePath, uploadInfo);
    }

    /**
     * Upload script in multi part
     *
     * @param filePath file path
     * @return Script script info
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public Script multiPartUploadScript(String filePath) throws CloudTestSDKException {
        UploadInfo uploadInfo = this.getUploadInfo(filePath, "script");
        this.uploadChunk(filePath, uploadInfo);
        return this.dumpScript(uploadInfo);
    }


    /**
     * Multipart upload APK to WeTest platform
     *
     * @param filePath 文件路径
     * @param projectId 项目ID
     * @return WTApp
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public WTApp multiPartUploadApkToWT(String filePath, String projectId) throws CloudTestSDKException {
        WTUploadInfo uploadInfo = this.getWTUploadInfo(filePath, projectId);
        WTApp app = this.dumpApkInWT(uploadInfo);
        this.uploadChunkWT(uploadInfo);
        try {
            for (int i = 0; i < 5; i++) {
                WTUploadResult result = this.getWTUploadResult(uploadInfo);
                if (result.status == -1) {
                    throw new CloudTestSDKException("upload file to wetest failed");
                }
                if (result.status == 1) {
                    app.appHashId = result.fileHashId;
                    return app;
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new CloudTestSDKException("get upload result interrupted by " + e.getMessage());
        }

        throw new CloudTestSDKException("get upload result from wetest failed, after tried 5 times");
    }

    /**
     * 分片上传IPA到WeTest平台
     *
     * @param filePath 文件路径
     * @param projectId 项目ID
     * @return WTApp
     * @throws CloudTestSDKException CloudTestSDKException
     */
    public WTApp multiPartUploadIpaToWT(String filePath, String projectId) throws CloudTestSDKException {
        WTUploadInfo uploadInfo = this.getWTUploadInfo(filePath, projectId);
        WTApp app = this.dumpIPAinWT(uploadInfo);
        this.uploadChunkWT(uploadInfo);
        try {
            for (int i = 0; i < 5; i++) {
                WTUploadResult result = this.getWTUploadResult(uploadInfo);
                if (result.status == -1) {
                    throw new CloudTestSDKException("upload file to wetest failed");
                }
                if (result.status == 1) {
                    app.appHashId = result.fileHashId;
                    return app;
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new CloudTestSDKException("get upload result interrupted by " + e.getMessage());
        }

        throw new CloudTestSDKException("get upload result from wetest failed, after tried 5 times");
    }


    private void uploadChunkWT(WTUploadInfo uploadInfo) throws CloudTestSDKException {
        File f = new File(uploadInfo.path);
        FileInputStream is;
        try {
            is = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            throw new CloudTestSDKException("file not found, filePath:" + uploadInfo.path);
        }

        for (int i = 0; i < uploadInfo.chunkNum; i++) {
            byte[] chunk;
            int chunkSize = 0;
            // 最后一次特殊处理
            if (i == uploadInfo.chunkNum - 1) {
                chunk = new byte[(int) (uploadInfo.size - uploadInfo.chunkSize * i)];
            } else {
                chunk = new byte[(int) uploadInfo.chunkSize];
            }
            try {
                chunkSize = is.read(chunk);
            } catch (IOException e) {
                e.printStackTrace();
            }
            UploadWTRequest request = new UploadWTRequest();
            request.setBody(chunk);
            request.setChunkNum(i + 1);
            request.setUploadId(uploadInfo.uploadId);
            System.out.println("upload chunk id=" + (i + 1) + " chunk size=" + chunkSize);
            this.internalRequest(request);
        }
        try {
            is.close();
        } catch (IOException e) {
            this.getLogger().info("close file err= " + e.getMessage());
            // ignore
        }
    }

    private void uploadChunk(String filePath, UploadInfo uploadInfo) throws CloudTestSDKException {
        File f = new File(filePath);
        FileInputStream is;
        try {
            is = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            throw new CloudTestSDKException("file not found, filePath:" + filePath);
        }

        for (int i = 0; i < uploadInfo.chunkNumber; i++) {
            byte[] chunk;
            int chunkSize = 0;
            // 最后一次特殊处理
            if (i == uploadInfo.chunkNumber - 1) {
                chunk = new byte[(int) (uploadInfo.fileSize - uploadInfo.chunkSize * i)];
            } else {
                chunk = new byte[(int) uploadInfo.chunkSize];
            }
            try {
                chunkSize = is.read(chunk);
            } catch (IOException e) {
                e.printStackTrace();
            }
            UploadRequest request = new UploadRequest();
            request.setFieldName("file");
            request.setFileName(uploadInfo.fileName);
            request.setFileMime("application/zip");
            request.setBody(chunk);
            request.setUploadId(uploadInfo.uploadId);
            request.setChunkNum(i + 1);
            System.out.println("upload chunk id=" + (i + 1) + " chunk size=" + chunkSize);
            this.internalRequest(request);
        }

    }

    private byte[] getIPADumpContent(String filePath) throws CloudTestSDKException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(bos);

        try {
            ZipFile zipFile = new ZipFile(filePath);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String[] elems = entry.getName().split("/");
                if (elems.length == 3 && elems[0].equals("Payload") && elems[2].equals("Info.plist")) {
                    InputStream is = zipFile.getInputStream(entry);
                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                    int nRead;
                    byte[] data = new byte[16384];

                    while ((nRead = is.read(data, 0, data.length)) != -1) {
                        buffer.write(data, 0, nRead);
                    }
                    ZipEntry en = new ZipEntry("Info.plist");
                    zos.putNextEntry(en);
                    zos.write(buffer.toByteArray());
                }
            }
            zos.closeEntry();
            zos.close();
        } catch (IOException e) {
            throw new CloudTestSDKException("get ipa dump file, io exception msg:" + e.getMessage());
        }
        return bos.toByteArray();
    }

    private byte[] getApkDumpContent(String filePath) throws CloudTestSDKException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(bos);
        try {

            ZipFile zipFile = new ZipFile(filePath);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry.getName().equals("AndroidManifest.xml") || entry.getName().equals("resources.arsc")) {
                    InputStream is = zipFile.getInputStream(entry);
                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                    int nRead;
                    byte[] data = new byte[16384];

                    while ((nRead = is.read(data, 0, data.length)) != -1) {
                        buffer.write(data, 0, nRead);
                    }

                    ZipEntry en = new ZipEntry(entry.getName());
                    zos.putNextEntry(en);
                    zos.write(buffer.toByteArray());
                }
            }
            zos.closeEntry();
            zos.close();
        } catch (IOException e) {
            throw new CloudTestSDKException("get apk dump file, io exception msg:" + e.getMessage());
        }

        return bos.toByteArray();
    }

    private App dumpApk(String filePath, UploadInfo uploadInfo) throws CloudTestSDKException {
        DumpAppRequest request = new DumpAppRequest();
        request.setFileName(uploadInfo.fileName);
        request.setUploadId(uploadInfo.uploadId);
        byte[] dumpContent = getApkDumpContent(filePath);

        request.setBody(dumpContent);
        DumpAppResponse rsp;
        String rspStr = "";

        try {
            Type type = new TypeToken<DumpAppResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
            return rsp.app;
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
    }

    private App dumpIPA(String filePath, UploadInfo uploadInfo) throws CloudTestSDKException {
        DumpAppRequest request = new DumpAppRequest();
        request.setFileName(uploadInfo.fileName);
        request.setUploadId(uploadInfo.uploadId);
        byte[] dumpContent = getIPADumpContent(filePath);
        request.setBody(dumpContent);
        DumpAppResponse rsp;
        String rspStr = "";

        try {
            Type type = new TypeToken<DumpAppResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
            return rsp.app;
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
    }

    private WTApp dumpIPAinWT(WTUploadInfo uploadInfo) throws CloudTestSDKException {
        DumpAppWTRequest request = new DumpAppWTRequest();
        request.setFileName(uploadInfo.name);
        request.setUploadId(uploadInfo.uploadId);
        byte[] dumpContent = getIPADumpContent(uploadInfo.path);
        request.setBody(dumpContent);
        JsonResponseModel<WTApp> rsp = null;
        String rspStr = "";
        try {
            Type type = new TypeToken<JsonResponseModel<WTApp>>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
            return rsp.data;
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
    }

    private WTApp dumpApkInWT(WTUploadInfo uploadInfo) throws CloudTestSDKException {
        DumpAppWTRequest request = new DumpAppWTRequest();
        request.setFileName(uploadInfo.name);
        request.setUploadId(uploadInfo.uploadId);
        byte[] dumpContent = getApkDumpContent(uploadInfo.path);
        request.setBody(dumpContent);
        JsonResponseModel<WTApp> rsp = null;
        String rspStr = "";
        try {
            Type type = new TypeToken<JsonResponseModel<WTApp>>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
            return rsp.data;
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
    }

    private Script dumpScript(UploadInfo uploadInfo) throws CloudTestSDKException {
        DumpScriptRequest request = new DumpScriptRequest();
        request.setUploadId(uploadInfo.uploadId);
        DumpScriptResponse rsp;
        String rspStr = "";
        try {
            Type type = new TypeToken<DumpScriptResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
            return rsp.script;
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
    }

    private UploadInfo getUploadInfo(String filePath, String uploadFileType) throws CloudTestSDKException {
        File f = new File(filePath);
        GetUploadFileIdResponse rsp;
        String rspStr = "";
        try {
            long fileSize = f.length();
            String fileName = f.getName();

            GetUploadFileIdRequest request = new GetUploadFileIdRequest();
            request.setFileName(fileName);
            request.setFileSize(fileSize);
            request.setUploadFileType(uploadFileType);
            Type type = new TypeToken<GetUploadFileIdResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);

            rsp.uploadInfo.fileName = fileName;
            rsp.uploadInfo.fileType = uploadFileType;
            rsp.uploadInfo.fileSize = fileSize;
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }
        return rsp.uploadInfo;
    }

    private WTUploadInfo getWTUploadInfo(String filePath, String projectId) throws CloudTestSDKException {
        File f = new File(filePath);
        long fileSize = f.length();
        String fileName = f.getName();
        String md5;
        try {
            InputStream is = Files.newInputStream(Paths.get(filePath));
            md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(is);
            is.close();
        } catch (Exception e) {
            throw new CloudTestSDKException(e.getMessage());
        }

        GetUploadIdFromWTRequest request = new GetUploadIdFromWTRequest();
        request.setCsize(10485760);
        request.setSize(fileSize);
        request.setName(fileName);
        request.setProject(projectId);

        JsonResponseModel<GetUploadIdFromWTResponse> rsp = null;
        String rspStr = "";
        try {
            Type type = new TypeToken<JsonResponseModel<GetUploadIdFromWTResponse>>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        }

        WTUploadInfo uploadInfo = new WTUploadInfo();
        uploadInfo.name = fileName;
        uploadInfo.size = fileSize;
        uploadInfo.path = filePath;
        uploadInfo.md5 = md5;
        uploadInfo.uploadId = rsp.data.id;
        uploadInfo.chunkNum = rsp.data.chunkNumber;
        uploadInfo.chunkSize = rsp.data.chunkSize;
        return uploadInfo;
    }

    private WTUploadResult getWTUploadResult(WTUploadInfo uploadInfo) throws CloudTestSDKException {
        GetUploadResultFromWTResponse rsp;
        String rspStr = "";
        GetUploadResultFromWTRequest request = new GetUploadResultFromWTRequest();
        request.setId(uploadInfo.uploadId);
        try {
            Type type = new TypeToken<GetUploadResultFromWTResponse>() {
            }.getType();
            rspStr = this.internalRequest(request);
            rsp = gson.fromJson(rspStr, type);
            if (rsp.uploadResult == null) {
                rsp.uploadResult = new WTUploadResult();
            }
            rsp.uploadResult.status = rsp.ret;
            return rsp.uploadResult;
        } catch (JsonSyntaxException e) {
            throw new CloudTestSDKException("response message: " + rspStr + ".\n Error message: " + e.getMessage());
        } catch (CloudTestSDKException e) {
            if (e.getErrorCode() == -1) {
                WTUploadResult result = new WTUploadResult();
                result.status = -1;
                return result;
            } else {
                throw e;
            }
        }
    }
}

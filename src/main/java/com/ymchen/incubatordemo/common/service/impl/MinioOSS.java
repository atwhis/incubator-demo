package com.ymchen.incubatordemo.common.service.impl;

import com.ymchen.incubatordemo.common.service.OSSService;
import io.minio.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Data
public class MinioOSS extends OSSService {
    private MinioClient getClient() {

        return MinioClient.builder()
                .endpoint(ossProperties.getUrl())
                .credentials(ossProperties.getAccessKey(), ossProperties.getSecretKey())
                .build();
    }

    private void createBucketIfNoExist(MinioClient minioClient) {
        try {
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(ossProperties.getBucketName()).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(ossProperties.getBucketName()).build());
            }
        } catch (Exception ex) {
            log.error("create bucket error:{}", ex.getMessage());
        }

    }

    private String getFileName(String fileName) {
        return UUID.randomUUID().toString().replaceAll("-", "")
                + fileName.substring(fileName.lastIndexOf("."));
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        try {
            MinioClient client = getClient();
            createBucketIfNoExist(client);
            InputStream inputStream = multipartFile.getInputStream();
            String fileName = getFileName(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            client.putObject(PutObjectArgs.builder().bucket(ossProperties.getBucketName())
                    .object(fileName).stream(inputStream, inputStream.available(), -1).build());
            return ossProperties.getUrl() + "/" + ossProperties.getBucketName() + "/" + fileName;
        } catch (Exception ex) {
            log.error("file upload error:{}", ex.getMessage());
        }

        return null;
    }

    @Override
    public InputStream downloadFile(String fileName) {
        MinioClient client = getClient();
        try {
            return client.getObject(
                    GetObjectArgs.builder().bucket(ossProperties.getBucketName()).object(fileName).build());
        } catch (Exception exception) {
            log.error("download file error:{}", exception.getMessage());
        }
        return null;
    }

    @Override
    public void testUpload() {

    }

    private void downloadToLocal() {
        MinioClient minioClient = getClient();
        try {
            minioClient.downloadObject(
                    DownloadObjectArgs.builder()
                            .bucket(ossProperties.getBucketName())
                            .object("tspvg.txt")
                            .filename("/Users/ymchen/Downloads/abc.txt")
                            .build());
        } catch (Exception e) {
            log.error("file download error:{}", e.getMessage());
        }
    }

    private void uploadLocalFile() {
        try {
            MinioClient minioClient = getClient();
            createBucketIfNoExist(minioClient);
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(ossProperties.getBucketName())
                            .object("credentials.json")
                            .filename("/Users/ymchen/Downloads/credentials.json")
                            .build());
            log.info("minio file upload success");

        } catch (Exception e) {
            log.error("minio file upload error:{}", e.getMessage());
        }
    }
}

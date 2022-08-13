package com.ymchen.incubatordemo.common.service;

import com.ymchen.incubatordemo.common.properties.OssProperties;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Data
public abstract class OSSService {

    public OssProperties ossProperties;

    public abstract void testUpload();

    public abstract String uploadFile(MultipartFile multipartFile);

    public abstract InputStream downloadFile(String fileUrl);


}

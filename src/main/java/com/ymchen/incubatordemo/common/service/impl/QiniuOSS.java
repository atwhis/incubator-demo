package com.ymchen.incubatordemo.common.service.impl;

import com.ymchen.incubatordemo.common.service.OSSService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Slf4j
@Data
public class QiniuOSS extends OSSService {

    @Override
    public void testUpload() {

    }

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        return null;
    }

    @Override
    public InputStream downloadFile(String fileUrl) {
        return null;
    }
}

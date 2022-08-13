package com.ymchen.incubatordemo.controller;

import com.ymchen.incubatordemo.common.service.OSSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@Slf4j
@RestController
@RequestMapping("oss")
public class OSSController {

    private OSSService ossService;

    @Autowired
    public void setOssService(OSSService ossService) {
        this.ossService = ossService;
    }

    @RequestMapping("hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("upload")
    public Object upload(MultipartFile file) {
        ossService.uploadFile(file);
        return "success";
    }

    @RequestMapping("download")
    public Object download(@RequestParam("fileName") String fileUrl, HttpServletResponse response) throws Exception {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/")+1);
        InputStream inputStream = ossService.downloadFile(fileName);
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        OutputStream outStr = null;
        outStr = response.getOutputStream();
        byte[] buf = new byte[1024];
        int index = 0;
        while ((index = inputStream.read(buf)) != -1) {
            outStr.write(buf, 0, index);
            outStr.flush();
        }
        outStr.close();
        inputStream.close();
        return "success";
    }
}

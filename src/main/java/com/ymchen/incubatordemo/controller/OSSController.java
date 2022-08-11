package com.ymchen.incubatordemo.controller;

import com.ymchen.incubatordemo.common.service.OSSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("upload")
    public Object upload() {
        ossService.testUpload();
        log.info("file upload invoke");
        return "success";
    }
}

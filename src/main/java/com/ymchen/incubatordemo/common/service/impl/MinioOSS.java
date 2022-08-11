package com.ymchen.incubatordemo.common.service.impl;

import com.ymchen.incubatordemo.common.service.OSSService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MinioOSS implements OSSService {

    @Override
    public void testUpload() {
      log.info("minio file upload");
    }
}

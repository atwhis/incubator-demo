package com.ymchen.incubatordemo.common.service.impl;

import com.ymchen.incubatordemo.common.service.OSSService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QiniuOSS implements OSSService {

    @Override
    public void testUpload() {
      log.info("qiniu file upload");
    }
}

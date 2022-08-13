package com.ymchen.incubatordemo.common.config;

import com.ymchen.incubatordemo.common.properties.OssProperties;
import com.ymchen.incubatordemo.common.service.OSSService;
import com.ymchen.incubatordemo.common.service.impl.MinioOSS;
import com.ymchen.incubatordemo.common.service.impl.QiniuOSS;
import com.ymchen.incubatordemo.common.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(OssProperties.class)
public class OSSConfig {

    @Bean
    @ConditionalOnProperty(name = "oss.type", havingValue = "minio")
    @ConditionalOnMissingBean(OSSService.class)
    //@ConditionalOnExpression("'${oss.type}'.equals('minio')")
    public MinioOSS minioOss(OssProperties ossProperties) {
        log.info(">>>>>>>>>minio :{}", GsonUtil.getInstance().toJson(ossProperties));
        MinioOSS minioOSS = new MinioOSS();
        minioOSS.setOssProperties(ossProperties);
        return minioOSS;
    }

    @Bean
    @ConditionalOnProperty(name = "oss.type", havingValue = "qiniu")
    @ConditionalOnMissingBean(OSSService.class)
    //@ConditionalOnBean(OSSService.class)
    //@ConditionalOnExpression("'${oss.type}'.equals('qiniu')")
    public QiniuOSS qiniuOSS(OssProperties ossProperties) {
        log.info(">>>>>>>>>qiniu :{}", GsonUtil.getInstance().toJson(ossProperties));
        QiniuOSS qiniuOSS = new QiniuOSS();
        qiniuOSS.setOssProperties(ossProperties);
        return qiniuOSS;
    }

}

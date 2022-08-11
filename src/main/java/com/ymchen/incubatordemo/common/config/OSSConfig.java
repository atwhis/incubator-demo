package com.ymchen.incubatordemo.common.config;

import com.ymchen.incubatordemo.common.service.impl.MinioOSS;
import com.ymchen.incubatordemo.common.service.impl.QiniuOSS;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OSSConfig {


    @Bean
    @ConditionalOnProperty(prefix = "oss.minio", name = "enable", havingValue = "true")
    //@ConditionalOnExpression("'${oss.minio.enable}'.equals('true')")
    public MinioOSS minioOss() {
        return new MinioOSS();
    }

    @Bean
    @ConditionalOnProperty(prefix = "oss.qiniu", name = "enable", havingValue = "true")
    //@ConditionalOnExpression("'${oss.qiniu.enable}'.equals('true')")
    public QiniuOSS qiniuOSS() {
        return new QiniuOSS();
    }
}

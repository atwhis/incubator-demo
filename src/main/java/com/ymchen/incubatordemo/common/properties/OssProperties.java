package com.ymchen.incubatordemo.common.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "oss")
public class OssProperties {

    private String type;

    private String accessKey;

    private String secretKey;

    private String url;

    private String bucketName;
}

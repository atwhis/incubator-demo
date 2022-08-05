package com.ymchen.incubatordemo.common.model;

import com.ymchen.incubatordemo.common.enums.HttpTypeEnum;
import lombok.Data;

@Data
public class OkHttpParam {

    private String url;
    private String cookies;
    private String paramStr;
    private HttpTypeEnum httpTypeEnum;

}

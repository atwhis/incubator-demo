package com.ymchen.incubatordemo.common.utils;

import com.ymchen.incubatordemo.common.model.OkHttpParam;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.function.Function;

@Slf4j
public class OkHttpUtil {

    private final static OkHttpUtil INSTANCE = new OkHttpUtil();

    private static final OkHttpClient client = new OkHttpClient();

    private OkHttpUtil() {

    }

    public static OkHttpUtil getInstance() {
        return INSTANCE;
    }

    public <T> T http(OkHttpParam okHttpParam, Function<String, T> function) {
        String httpResponseJsonStr = getHttpResponse(okHttpParam);
        if (StringUtils.isNotBlank(httpResponseJsonStr)) {
            return function.apply(httpResponseJsonStr);
        }
        return null;
    }

    private String getHttpResponse(OkHttpParam okHttpParam) {
        Request request;
        switch (okHttpParam.getHttpTypeEnum()) {
            case GET:
                request = new Request.Builder().header("Cookie",
                        okHttpParam.getCookies())
                        .url(okHttpParam.getUrl() + "?" + okHttpParam.getParamStr()).build();
                break;
            case POST:
                RequestBody requestBody = RequestBody.create(okHttpParam.getParamStr(), MediaType.parse("application/json"));
                request = new Request.Builder().header("Cookie",
                        okHttpParam.getCookies())
                        .url(okHttpParam.getUrl())
                        .post(requestBody)
                        .build();
                break;
            default:
                throw new RuntimeException("http type error");
        }

        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            return body.string();
        } catch (IOException ex) {
            log.error("invoke http error:{}", ex.getMessage());
        }
        return null;
    }
}

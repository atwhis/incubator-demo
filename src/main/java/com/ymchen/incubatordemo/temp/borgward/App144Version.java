package com.ymchen.incubatordemo.temp.borgward;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class App144Version {

    static OkHttpClient client;

    static {
        client = new OkHttpClient();
    }

    public static void main(String[] args) {
        String param = "{\"env_type\":\"prod\",\"prod_type\":\"mmc\",\"service_name\":\"mmccoapi_100\",\"search\":\"/action/co/version/getUpdateInfo接口调用，参数 144\",\"data_type\":[\"prod_tomcat_mmc\",\"prod_tomcat_mmc_logicb\"],\"ip_list\":[\"10.212.125.26\",\"10.212.125.56\",\"10.212.125.24\",\"10.212.125.27\"],\"file_list\":[\"/usr/local/tomcat/logs/mmccoapi_log\"],\"start_time\":1658995200000,\"end_time\":1659412800000,\"search_fields\":[],\"search_context\":{}}";


        String filePath = "/Users/ymchen/IdeaProjects/personal/incubator-demo/doc/mobiles.xlsx";

        Set<Mobile> mobiles = getMobileBy144AppVersion(param);
        log.info("mobiles :{}", mobiles.size());
        File file=new File(filePath);
        EasyExcel.write(file, Mobile.class).sheet().doWrite(mobiles);

    }


    private static Set<Mobile> getMobileBy144AppVersion(String param) {
        Set<Mobile> mobiles = new HashSet<>();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), param);
        Request request = new Request.Builder().header("Cookie",
                "ldap-user=\"{\\\"admin\\\":false,\\\"authorityC\\\":false,\\\"authorityChain\\\":false,\\\"authorityL\\\":false,\\\"authorityS\\\":false,\\\"commentsProjectIds\\\":[],\\\"desName\\\":\\\"\\\",\\\"emailSuffix\\\":\\\"ucarinc.com\\\",\\\"emil\\\":\\\"yuanming.chen01@ucarinc.com\\\",\\\"enbaled\\\":false,\\\"loginId\\\":\\\"yuanming.chen01\\\",\\\"projectIds\\\":[610208,610337,610217,610206,610258,610214,610314,610215,610325,610432,610422,610279,610202,610188,610210,610340,610198,610193,610281,610194,610191,610280,610187,610195,610199],\\\"userName\\\":\\\"yuanming.chen01\\\",\\\"xUser\\\":false}\"; ldap-token=3axuRKJxx99X9aVfOpFB%2F8NPfOgrnu6W3ek8DJOXyI8zQpHfsX149F66r7v5TNilwtnQ57CXynFC%0AAyva49ApoKELSJi7P4p2ltsPlBg2hiD7s0N%2F2%2F1s6a36jz9s0peOO9d29T6A8Dk%3D; uago_sessionid=uagosession-fe76d962-4817-45ae-9a46-2c937a824c21; csrftoken=LsD2rE7wU5vSdEFt17wMJ22V2bhWxc1T; djangoauth=3zd48gfqwdp5l3nxeo7xi4memi89l580")
                .url("https://usop.10101111.com/ulog_lucky_api/mining/dataservice/v2/logsearch/user/data")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            Gson gson = new Gson();

            JsonObject jsonObject = gson.fromJson(body.string(), JsonObject.class);
            if (null != jsonObject && jsonObject.get("Code").getAsInt() == 200) {
                JsonElement data = jsonObject.get("Data");
                String asString = data.getAsString();
                JSONObject jsonObject1 = JSON.parseObject(asString);
                String total = jsonObject1.getJSONObject("hits").get("total").toString();
                log.info(">>>>>>>total:{}", total);
                JSONArray jsonArray = jsonObject1.getJSONObject("hits").getJSONArray("hits");
                for (int i = 0; i < jsonArray.size(); i++) {
                    String source = jsonArray.getJSONObject(i).getJSONObject("_source").get("message").toString();
                    if (StringUtils.isNotBlank(source)) {
                        int index = source.indexOf("参数：");
                        if (0 < index) {
                            String substring = source.substring(index + 3);
                            JSONObject jsonObject2 = JSON.parseObject(substring);
                            Object grayStr = jsonObject2.get("grayStr");
                            if (null != grayStr && StringUtils.isNotBlank(grayStr.toString())) {
                                Mobile mobile=new Mobile();
                                mobile.setMobile(grayStr.toString());
                                mobiles.add(mobile);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mobiles;
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    static
    class Mobile {
        public Mobile() {

        }
        @ExcelProperty(value = "用户手机号", index = 0)
        String mobile;
    }
}

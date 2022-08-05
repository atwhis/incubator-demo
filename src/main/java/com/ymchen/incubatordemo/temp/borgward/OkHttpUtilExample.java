package com.ymchen.incubatordemo.temp.borgward;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ymchen.incubatordemo.common.enums.HttpTypeEnum;
import com.ymchen.incubatordemo.common.model.OkHttpParam;
import com.ymchen.incubatordemo.common.utils.GsonUtil;
import com.ymchen.incubatordemo.common.utils.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class OkHttpUtilExample {
    public static void main(String[] args) {
        log.info("customerName: {}", testRequest_getCustomerName("LXVJ2GEC7KA043009"));
        log.info("customerInfo: {}", testPost_getUserInfo("LXVJ2GEC7KA043009"));
    }


    private static String testRequest_getCustomerName(String vin) {
        OkHttpParam okHttpParam = new OkHttpParam();
        okHttpParam.setUrl("http://mng.tsp.borgward.com.cn/tspeapi/vehiclecustomer/getCustomerUserInfoByVin");
        okHttpParam.setParamStr("vin=" + vin);
        okHttpParam.setHttpTypeEnum(HttpTypeEnum.GET);
        okHttpParam.setCookies("SECKEY_ABVK=FtDCBp1y53ZmlGOMImQKO81k4waaITLWxFZ/75eMnG0%3D; BMAP_SECKEY=1cVqSXD_2i_qyCKgHALTAiBtSG5i2TJ_4HdQpXQxegqYzhuVzEfPkQGW4QQPYmocjWtz1Gk1cFVo0S9wL9Z48Cw7de0C2IITq4Aiytc-CRSeCbMDqjcA9x_9Ym-OELYgrFjgnfFud_Nw5PYIPulWh1MzL5LCd6UFR8nRUJahEa538A0oN_hLCcKt_jQuU87g; ucarincoam-token=ST-680f744c6bd54266991724d4d356550d; tsp-intranet-prod-sid=D517F965CE87A32A9DAD05AD95616AFD");

        return OkHttpUtil.getInstance().http(okHttpParam, responseStr -> {
            JsonObject jsonObject = GsonUtil.getInstance().fromJson(responseStr, JsonObject.class);
            if (null != jsonObject && jsonObject.get("success").getAsBoolean()) {
                JsonElement jsonElement = jsonObject.get("data").getAsJsonObject().get("customerName");
                if (null != jsonElement && !jsonElement.isJsonNull() && StringUtils.isNotBlank(jsonElement.getAsString())) {
                    return jsonElement.getAsString();
                }
            }
            return null;
        });
    }

    private static Map<String, String> testPost_getUserInfo(String vin) {
        OkHttpParam okHttpParam = new OkHttpParam();
        okHttpParam.setUrl("http://mng.tsp.borgward.com.cn/tspeapi/vgdata/getAllLastStatus");
        okHttpParam.setParamStr("{\"vin\":\"" + vin + "\"}");
        okHttpParam.setHttpTypeEnum(HttpTypeEnum.POST);
        okHttpParam.setCookies("SECKEY_ABVK=FtDCBp1y53ZmlGOMImQKO81k4waaITLWxFZ/75eMnG0%3D; BMAP_SECKEY=1cVqSXD_2i_qyCKgHALTAiBtSG5i2TJ_4HdQpXQxegqYzhuVzEfPkQGW4QQPYmocjWtz1Gk1cFVo0S9wL9Z48Cw7de0C2IITq4Aiytc-CRSeCbMDqjcA9x_9Ym-OELYgrFjgnfFud_Nw5PYIPulWh1MzL5LCd6UFR8nRUJahEa538A0oN_hLCcKt_jQuU87g; ucarincoam-token=ST-680f744c6bd54266991724d4d356550d; tsp-intranet-prod-sid=D517F965CE87A32A9DAD05AD95616AFD");

        return OkHttpUtil.getInstance().http(okHttpParam, responseStr -> {
            JsonObject jsonObject = GsonUtil.getInstance().fromJson(responseStr, JsonObject.class);
            Map<String, String> map = new HashMap<>();
            if (null != jsonObject && jsonObject.get("success").getAsBoolean()) {
                JsonObject data = jsonObject.get("data").getAsJsonObject().getAsJsonObject();
                if (null != data.get("userPhone") && !data.get("userPhone").isJsonNull()) {
                    map.put("userPhone", data.get("userPhone").getAsString());
                }
                if (null != data.get("userName") && !data.get("userName").isJsonNull()) {
                    map.put("userName", data.get("userName").getAsString());
                }
            }
            return map;
        });
    }
}

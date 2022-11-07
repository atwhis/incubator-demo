package com.ymchen.incubatordemo.examples.liteflow.controller;


import com.ymchen.incubatordemo.examples.liteflow.PriceContext;
import com.ymchen.incubatordemo.examples.liteflow.vo.PriceVo;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RefreshScope
@RequestMapping("liteflow")
public class LiteFlowController {

    @Autowired
    private FlowExecutor flowExecutor;

    @Value("${liteFlowChain}")
    private String chain;

    @RequestMapping("/test")
    public Object liteFlowTest() {
        PriceVo priceVo = PriceVo.builder().orderNo("NO.123456789").build();
        PriceContext priceContext = PriceContext.builder()
                .totalAmount(BigDecimal.valueOf(100.0))
                .couponAmount(BigDecimal.valueOf(3.0))
                .discountRate(BigDecimal.valueOf(0.9))
                .fullReducePreAmount(BigDecimal.valueOf(90.0))
                .fullReduceAmount(BigDecimal.valueOf(5.0))
                .interestAmount(BigDecimal.valueOf(1.0))
                .postAmount(BigDecimal.valueOf(0.5))
                .build();
        LiteflowResponse response = flowExecutor.execute2Resp(chain, priceVo, priceContext);

        return response.getContextBean(PriceContext.class).getPriceStepVos();
    }
}

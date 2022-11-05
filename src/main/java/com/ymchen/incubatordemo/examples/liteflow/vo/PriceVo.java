package com.ymchen.incubatordemo.examples.liteflow.vo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PriceVo {
    private String orderNo;

    private Double totalAmount;

}

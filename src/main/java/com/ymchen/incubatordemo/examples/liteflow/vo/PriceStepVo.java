package com.ymchen.incubatordemo.examples.liteflow.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class PriceStepVo {
    private String name;

    private BigDecimal changeAmount;

    private BigDecimal preAmount;

    private BigDecimal currentAmount;
}

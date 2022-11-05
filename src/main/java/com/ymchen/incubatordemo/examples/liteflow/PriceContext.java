package com.ymchen.incubatordemo.examples.liteflow;

import com.ymchen.incubatordemo.examples.liteflow.vo.PriceStepVo;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Builder
@Data
public class PriceContext {

    private String orderNo;

    private BigDecimal totalAmount;

    private BigDecimal discountRate;

    private BigDecimal interestAmount;

    private BigDecimal fullReduceAmount;

    /**
     * 满减满足的金额
     */
    private BigDecimal fullReducePreAmount;

    private BigDecimal postAmount;

    private BigDecimal couponAmount;

    /**
     * 各步骤的计算规则
     */
    private List<PriceStepVo> priceStepVos;

    public void addPriceStep(PriceStepVo priceStepVo) {
        if (null == this.getPriceStepVos()) {
            this.priceStepVos = new ArrayList<>();
        }
        this.priceStepVos.add(priceStepVo);
    }

    public PriceStepVo getLatestStepAmountVo() {
        if (CollectionUtils.isEmpty(priceStepVos)) {
            return null;
        } else {
            return priceStepVos.get(priceStepVos.size() - 1);
        }
    }
}

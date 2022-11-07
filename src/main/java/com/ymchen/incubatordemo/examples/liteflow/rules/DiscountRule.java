package com.ymchen.incubatordemo.examples.liteflow.rules;


import com.ymchen.incubatordemo.examples.liteflow.PriceContext;
import com.ymchen.incubatordemo.examples.liteflow.PriceTypeEnum;
import com.ymchen.incubatordemo.examples.liteflow.vo.PriceStepVo;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 折扣
 */
@Component("discount")
@Slf4j
public class DiscountRule extends NodeComponent {

    @Override
    public void process() throws Exception {
        PriceContext priceContext = this.getContextBean(PriceContext.class);
        BigDecimal preAmount = priceContext.getLatestStepAmountVo().getCurrentAmount();
        BigDecimal currAmount = preAmount.multiply(priceContext.getDiscountRate()).setScale(2, RoundingMode.HALF_UP);

        PriceStepVo discount = PriceStepVo.builder().name(PriceTypeEnum.DISCOUNT.getDescribe()).preAmount(preAmount)
                .currentAmount(currAmount).changeAmount(currAmount.subtract(preAmount)).build();
        priceContext.addPriceStep(discount);
        log.info("计算折扣...");
    }

    @Override
    public boolean isAccess() {
        PriceContext contextBean = this.getContextBean(PriceContext.class);
        if (contextBean.getDiscountRate().compareTo(BigDecimal.ZERO) > 0 && contextBean.getDiscountRate().compareTo(BigDecimal.ONE) < 0) {
            return true;
        } else {
            return false;
        }
    }
}

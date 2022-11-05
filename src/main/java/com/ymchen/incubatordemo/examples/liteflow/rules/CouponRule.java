package com.ymchen.incubatordemo.examples.liteflow.rules;


import com.ymchen.incubatordemo.examples.liteflow.PriceContext;
import com.ymchen.incubatordemo.examples.liteflow.PriceTypeEnum;
import com.ymchen.incubatordemo.examples.liteflow.vo.PriceStepVo;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 优惠卷规则
 */
@Component("coupon")
@Slf4j
public class CouponRule extends NodeComponent {

    @Override
    public void process() throws Exception {

        PriceContext priceContext = this.getContextBean(PriceContext.class);
        BigDecimal preAmount = priceContext.getLatestStepAmountVo().getCurrentAmount();
        BigDecimal currAmount = preAmount.subtract(priceContext.getCouponAmount());

        PriceStepVo coupon = PriceStepVo.builder().name(PriceTypeEnum.COUPON.getDescribe()).preAmount(preAmount)
                .currentAmount(currAmount).changeAmount(currAmount.subtract(preAmount)).build();
        priceContext.addPriceStep(coupon);
        log.info("计算优惠卷...");
    }

    @Override
    public boolean isAccess() {
        PriceContext contextBean = this.getContextBean(PriceContext.class);
        if (BigDecimal.ZERO.compareTo(contextBean.getCouponAmount()) < 0) {
            return true;
        } else {
            return false;
        }
    }
}

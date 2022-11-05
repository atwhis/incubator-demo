package com.ymchen.incubatordemo.examples.liteflow.rules;


import com.ymchen.incubatordemo.examples.liteflow.PriceContext;
import com.ymchen.incubatordemo.examples.liteflow.PriceTypeEnum;
import com.ymchen.incubatordemo.examples.liteflow.vo.PriceStepVo;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 包邮规则
 */
@Slf4j
@Component("freePost")
public class FreePostRule extends NodeComponent {
    @Override
    public void process() throws Exception {
        PriceContext priceContext = this.getContextBean(PriceContext.class);
        BigDecimal preAmount = priceContext.getLatestStepAmountVo().getCurrentAmount();
        BigDecimal currAmount = preAmount.subtract(priceContext.getPostAmount());

        PriceStepVo freeInterest = PriceStepVo.builder().name(PriceTypeEnum.FREEPOST.getDescribe()).preAmount(preAmount)
                .currentAmount(currAmount).changeAmount(currAmount.subtract(preAmount)).build();
        priceContext.addPriceStep(freeInterest);
        log.info("计算包邮...{}");
    }

    @Override
    public boolean isAccess() {
        PriceContext contextBean = this.getContextBean(PriceContext.class);
        if (contextBean.getPostAmount().compareTo(BigDecimal.ZERO) > 0) {
            return true;
        } else {
            return false;
        }
    }
}

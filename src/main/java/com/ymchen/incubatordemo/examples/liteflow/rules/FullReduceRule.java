package com.ymchen.incubatordemo.examples.liteflow.rules;


import com.ymchen.incubatordemo.examples.liteflow.PriceContext;
import com.ymchen.incubatordemo.examples.liteflow.PriceTypeEnum;
import com.ymchen.incubatordemo.examples.liteflow.vo.PriceStepVo;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 满减
 */
@Slf4j
@Component("fullReduce")
public class FullReduceRule extends NodeComponent {

    @Override
    public void process() throws Exception {
        PriceContext priceContext = this.getContextBean(PriceContext.class);
        BigDecimal preAmount = priceContext.getLatestStepAmountVo().getCurrentAmount();
        BigDecimal currAmount = preAmount.subtract(priceContext.getInterestAmount());
        PriceStepVo freeInterest = PriceStepVo.builder().name(PriceTypeEnum.FULLREDUCE.getDescribe()).preAmount(preAmount)
                .currentAmount(currAmount).changeAmount(currAmount.subtract(preAmount)).build();
        priceContext.addPriceStep(freeInterest);
        log.info("计算免息...{}");
    }

    @Override
    public boolean isAccess() {
        PriceContext contextBean = this.getContextBean(PriceContext.class);
        //满减前金额大于 条件金额 且 满减金额>0
        if (contextBean.getFullReduceAmount().compareTo(BigDecimal.ZERO) > 0
                && contextBean.getFullReducePreAmount().compareTo(contextBean.getLatestStepAmountVo().getCurrentAmount()) < 0) {
            return true;
        } else {
            return false;
        }
    }
}

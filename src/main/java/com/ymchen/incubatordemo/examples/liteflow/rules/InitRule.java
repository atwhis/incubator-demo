package com.ymchen.incubatordemo.examples.liteflow.rules;


import com.ymchen.incubatordemo.examples.liteflow.PriceContext;
import com.ymchen.incubatordemo.examples.liteflow.PriceTypeEnum;
import com.ymchen.incubatordemo.examples.liteflow.vo.PriceStepVo;
import com.ymchen.incubatordemo.examples.liteflow.vo.PriceVo;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 初始化
 */
@Slf4j
@Component("init")
public class InitRule extends NodeComponent {
    @Override
    public void process() throws Exception {
        PriceVo priceVo = this.getSlot().getRequestData();
        PriceContext context = this.getContextBean(PriceContext.class);
        context.setOrderNo(priceVo.getOrderNo());
        PriceStepVo priceStepVo = PriceStepVo.builder().name(PriceTypeEnum.INIT.getDescribe()).preAmount(context.getTotalAmount())
                .changeAmount(null).currentAmount(context.getTotalAmount()).build();
        context.addPriceStep(priceStepVo);
        log.info("数据初始化...");
    }

    @Override
    public boolean isAccess() {
        PriceVo req = this.getSlot().getRequestData();
        if (req != null) {
            return true;
        } else {
            return false;
        }
    }
}

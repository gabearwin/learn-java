package xyz.gabear.learn.designpattern.strategy;

import org.junit.Test;
import xyz.gabear.learn.designpattern.strategy.impl.ChargeVipStrategyImpl;

import java.math.BigDecimal;

public class ChargeStrategyTest {

    @Test
    public void doChargeTest() {
        ChargeContext chargeContext = new ChargeContext(new ChargeVipStrategyImpl());
        chargeContext.doCharge(BigDecimal.valueOf(100));
    }

}
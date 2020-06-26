package xyz.gabear.learn.designpattern.strategy.impl;

import org.springframework.stereotype.Service;
import xyz.gabear.learn.designpattern.strategy.ChargeStrategy;

import java.math.BigDecimal;

@Service
public class ChargeVipStrategyImpl implements ChargeStrategy {
    private BigDecimal discount = BigDecimal.valueOf(0.75);

    @Override
    public void doCharge(BigDecimal fee) {
        System.out.println("VIP用户收费：" + discount.multiply(fee));
    }
}

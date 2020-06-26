package xyz.gabear.learn.designpattern.strategy.impl;

import org.springframework.stereotype.Service;
import xyz.gabear.learn.designpattern.strategy.ChargeStrategy;

import java.math.BigDecimal;

@Service
public class ChargeNormalStrategyImpl implements ChargeStrategy {
    private BigDecimal discount = BigDecimal.ONE;

    @Override
    public void doCharge(BigDecimal fee) {
        System.out.println("普通用户收费：" + discount.multiply(fee));
    }
}

package xyz.gabear.learn.designpattern.strategy;

import java.math.BigDecimal;

public interface ChargeStrategy {
    void doCharge(BigDecimal fee);
}

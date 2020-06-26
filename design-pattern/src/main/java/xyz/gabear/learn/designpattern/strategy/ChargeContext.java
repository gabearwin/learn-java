package xyz.gabear.learn.designpattern.strategy;

import java.math.BigDecimal;

/**
 * 策略模式涉及到三个角色：
 * <p>
 * ● 环境(Context)角色：持有一个Strategy的引用。
 * <p>
 * ● 抽象策略(Strategy)角色：这是一个抽象角色，通常由一个接口或抽象类实现。此角色给出所有的具体策略类所需的接口。
 * <p>
 * ● 具体策略(ConcreteStrategy)角色：包装了相关的算法或行为。
 */
public class ChargeContext {
    private ChargeStrategy chargeStrategy;

    public ChargeContext(ChargeStrategy chargeStrategy) {
        this.chargeStrategy = chargeStrategy;
    }

    public void doCharge(BigDecimal fee) {
        chargeStrategy.doCharge(fee);
    }
}

package xyz.gabear.learn.designpattern.proxy.staticproxy;

import xyz.gabear.learn.designpattern.proxy.Car;

/**
 * 静态代理--继承实现
 */
public class CarProxy1 extends Car {
    @Override
    public void move() {
        long start = System.currentTimeMillis();
        System.out.println("汽车行驶前");
        super.move();
        long end = System.currentTimeMillis();
        System.out.println("汽车行驶结束，用时 " + (end - start) + " 毫秒");
    }
}

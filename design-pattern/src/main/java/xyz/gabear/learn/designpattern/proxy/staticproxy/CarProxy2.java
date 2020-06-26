package xyz.gabear.learn.designpattern.proxy.staticproxy;

import xyz.gabear.learn.designpattern.proxy.Car;
import xyz.gabear.learn.designpattern.proxy.Moveable;

/**
 * 静态代理--聚合实现(推荐)
 */
public class CarProxy2 implements Moveable {

    private Car car;

    public CarProxy2(Car car) {
        super();
        this.car = car;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        System.out.println("汽车行驶前");
        car.move();
        long end = System.currentTimeMillis();
        System.out.println("汽车行驶结束，用时 " + (end - start) + " 毫秒");
    }
}

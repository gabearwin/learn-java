package xyz.gabear.learn.designpattern.proxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.gabear.learn.designpattern.proxy.jdkproxy.LogHandler;
import xyz.gabear.learn.designpattern.proxy.jdkproxy.TimeHandler;
import xyz.gabear.learn.designpattern.proxy.staticproxy.CarProxy1;
import xyz.gabear.learn.designpattern.proxy.staticproxy.CarProxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarTest {

    @Test
    public void staticProxy() {
        CarProxy1 proxy1 = new CarProxy1();
        proxy1.move();
        CarProxy2 proxy2 = new CarProxy2(new Car());
        proxy2.move();
    }

    @Test
    public void jdkProxy() {
        Car car = new Car();
        InvocationHandler timeHandler = new TimeHandler(car);
        Class<? extends Car> carClass = car.getClass();
        /*
         * 第一个参数：类加载器
         * 第二个参数：实现的接口
         * 第三个参数：InvocationHandler
         * 返回值：返回代理后的对象
         */
        Moveable timeMoveable = (Moveable) Proxy.newProxyInstance(carClass.getClassLoader(), carClass.getInterfaces(), timeHandler);
        timeMoveable.move();
        /*在这里输出：
        JDK时间代理开始
        汽车行驶中
        JDK时间代理结束*/

        InvocationHandler logHandler = new LogHandler(timeMoveable);
        Class<? extends Moveable> moveableClass = timeMoveable.getClass();
        Moveable logMoveable = (Moveable) Proxy.newProxyInstance(moveableClass.getClassLoader(), moveableClass.getInterfaces(), logHandler);
        logMoveable.move();
        /*在这里输出：
        JDK日志代理开始
        JDK时间代理开始
        汽车行驶中...
        JDK时间代理结束
        JDK日志代理结束*/
    }
}
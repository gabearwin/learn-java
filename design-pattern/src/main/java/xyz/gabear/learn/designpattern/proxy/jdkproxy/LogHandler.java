package xyz.gabear.learn.designpattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理. 使用请看 xyz.gabear.learn.designpattern.proxy.CarTest
 *
 * @see xyz.gabear.learn.designpattern.proxy.CarTest
 */
public class LogHandler implements InvocationHandler {

    private Object target;

    public LogHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK日志代理开始");
        Object result = method.invoke(target, args);
        System.out.println("JDK日志代理结束");
        return result;
    }
}

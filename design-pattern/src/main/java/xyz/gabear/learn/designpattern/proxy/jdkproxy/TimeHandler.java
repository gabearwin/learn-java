package xyz.gabear.learn.designpattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler {

    private Object target;

    public TimeHandler(Object target) {
        super();
        this.target = target;
    }

    /**
     * @param proxy  被代理对象
     * @param method 被代理对象的方法
     * @param args   上面方法的参数
     * @return 上面方法的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK时间代理开始");
        Object result = method.invoke(target, args);
        System.out.println("JDK时间代理结束");
        return result;
    }
}

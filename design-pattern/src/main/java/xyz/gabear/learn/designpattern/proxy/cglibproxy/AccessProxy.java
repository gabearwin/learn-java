package xyz.gabear.learn.designpattern.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class AccessProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {
        // 指定为哪个类创建子类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * 拦截所有目标类的方法的调用
     *
     * @param obj         目标类的实例
     * @param method      目标方法的反射对象
     * @param args        方法的参数
     * @param methodProxy 代理类的实例
     * @return 目标方法运行后的返回值
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib访问控制代理开始");
        // 代理类调用父类方法
        Object result = methodProxy.invokeSuper(obj, args);
        System.out.println("cglib访问控制代理结束");
        return result;
    }
}

package xyz.gabear.spring.starter;

import xyz.gabear.spring.beans.BeanFactory;
import xyz.gabear.spring.core.ClassScanner;
import xyz.gabear.spring.web.handler.HandlerManager;
import xyz.gabear.spring.web.server.TomcatServer;

import java.util.List;

public class MiniSpringApplication {
    public static void run(Class<?> cls, String[] args) {
        System.out.println("Hello mini-spring!");
        TomcatServer tomcatServer = new TomcatServer(args);
        try {
            tomcatServer.startServer();
            // 扫描并加载所有的类
            List<Class<?>> classList = ClassScanner.scanClasses(cls.getPackage().getName());
            // 初始化 bean
            BeanFactory.initBean(classList);
            // 解析获取一个请求也即 uri->method 的映射
            HandlerManager.resolveMappingHandler(classList);
            classList.forEach(it -> System.out.println(it.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

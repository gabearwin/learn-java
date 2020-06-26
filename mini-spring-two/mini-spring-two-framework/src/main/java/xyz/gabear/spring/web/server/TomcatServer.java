package xyz.gabear.spring.web.server;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import xyz.gabear.spring.web.servlet.DispatcherServlet;

public class TomcatServer {
    private Tomcat tomcat;
    private String[] args;

    public TomcatServer(String[] args) {
        this.args = args;
    }

    public void startServer() throws LifecycleException {
        tomcat = new Tomcat();
        // 这里先简单的写定一个端口。也可以拓展为通过参数来指定
        tomcat.setPort(6699);
        tomcat.start();

        // 新建一个容器
        Context context = new StandardContext();
        context.setPath("");
        context.addLifecycleListener(new Tomcat.FixContextListener());

        // // 创建一个 servlet
        // TestServlet testServlet = new TestServlet();
        // Tomcat.addServlet(context, "testServlet", testServlet).setAsyncSupported(true);
        // // 给 servlet 添加路径映射(http://localhost:6699/test.json)
        // context.addServletMappingDecoded("/test.json", "testServlet");

        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet).setAsyncSupported(true);
        context.addServletMappingDecoded("/", "dispatcherServlet");
        tomcat.getHost().addChild(context);

        Thread awaitThread = new Thread("tomcat_await_thread") {
            @Override
            public void run() {
                TomcatServer.this.tomcat.getServer().await();
            }
        };
        // 设置为非守护线程
        awaitThread.setDaemon(false);
        awaitThread.start();
    }
}

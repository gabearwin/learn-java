package xyz.gabear.learn.ssm.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(MyServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 获取在web.xml中配置的参数
        String name = sce.getServletContext().getInitParameter("name");
        logger.info("contextInitialized, parameter is " + name);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("contextDestroyed");
    }
}

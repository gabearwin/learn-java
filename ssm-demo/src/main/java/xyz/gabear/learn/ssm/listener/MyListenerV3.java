package xyz.gabear.learn.ssm.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("This is My Servlet 3.0 Listener")
public class MyListenerV3 implements ServletContextListener {
    private static Logger logger = LoggerFactory.getLogger(MyListenerV3.class);

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        logger.info("contextDestroyed");
        System.out.println("contextDestroyed");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        logger.info("contextInitialized");
    }
}

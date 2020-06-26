package xyz.gabear.learn.ssm.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener {
    private Logger logger = LoggerFactory.getLogger(MyHttpSessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("sessionCreated");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("sessionDestroyed");
    }
}

package xyz.gabear.learn.ssm.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;


public class MyServletRequestListener implements ServletRequestListener {
    private Logger logger = LoggerFactory.getLogger(MyServletRequestListener.class);

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        // 获取request的url链接中的参数
        String name = sre.getServletRequest().getParameter("name");
        logger.info("requestInitialized, name is " + name);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        logger.info("requestDestroyed");
    }


}

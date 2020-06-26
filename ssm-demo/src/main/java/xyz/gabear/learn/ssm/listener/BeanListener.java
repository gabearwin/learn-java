package xyz.gabear.learn.ssm.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

public class BeanListener implements HttpSessionBindingListener, HttpSessionActivationListener, Serializable {
    private static Logger logger = LoggerFactory.getLogger(BeanListener.class);

    private String username;
    private String password;

    // request.getSession().setAttribute("currentUser", new BeanListener());
    // session中对user对象有setAttribute操作会触发valueBound方法，有removeAttribute操作会触发valueUnbound方法
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        logger.info("valueBound " + event.getName());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        logger.info("valueUnbound " + event.getName());
    }

    // 钝化和活化需要实现HttpSessionActivationListener接口还需要实现Serializable接口
    // 钝化，将当前的session存储到文件中
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        logger.info("sessionWillPassivate " + se.getSource());
    }

    // 活化，从文件中读取session
    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        logger.info("sessionDidActivate " + se.getSource());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

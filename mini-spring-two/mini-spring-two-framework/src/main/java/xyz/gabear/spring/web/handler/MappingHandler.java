package xyz.gabear.spring.web.handler;

import xyz.gabear.spring.beans.BeanFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MappingHandler {
    private String uri;
    private Class<?> controller;    // 请求的 controller
    private Method method;          // 请求的方法
    private String[] args;          // 请求的参数

    MappingHandler(String uri, Class<?> cls, Method method, String[] args) {
        this.uri = uri;
        this.controller = cls;
        this.method = method;
        this.args = args;
    }

    public boolean handle(ServletRequest req, ServletResponse res) throws IllegalAccessException,
            InstantiationException, InvocationTargetException, IOException {
        String requestUri = ((HttpServletRequest) req).getRequestURI();
        if (!uri.equals(requestUri)) {
            return false;
        }

        Object[] parameters = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            parameters[i] = req.getParameter(args[i]);
        }

        Object ctl = BeanFactory.getBean(controller);
        Object response = method.invoke(ctl, parameters);
        res.getWriter().println(response.toString());
        return true;
    }

}

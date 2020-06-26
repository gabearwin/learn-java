package xyz.gabear.spring.web.servlet;

import xyz.gabear.spring.web.handler.HandlerManager;
import xyz.gabear.spring.web.handler.MappingHandler;

import javax.servlet.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class DispatcherServlet implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.getWriter().println("This is dispatcherServlet.");
        for (MappingHandler mappingHandler : HandlerManager.mappingHandlerList) {
            try {
                // 反射执行，写入 response
                if (mappingHandler.handle(req, res)) {
                    return;
                }
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

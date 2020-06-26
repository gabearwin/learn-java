package xyz.gabear.learn.ssm.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

public class ApiAuthFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(ApiAuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // filterConfig.getInitParameter()获取自定义的某些参数
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
        // ctx.getBean()操作可以完成依赖注入
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // doSomething...
        /*
        // 转发
        HttpServletRequest request1 = (HttpServletRequest) request;
        request1.getRequestDispatcher("/login.jsp").forward(request,response);
        // 重定向
        HttpServletResponse response1 = (HttpServletResponse) response;
        response1.sendRedirect("/login.jsp");
        */
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

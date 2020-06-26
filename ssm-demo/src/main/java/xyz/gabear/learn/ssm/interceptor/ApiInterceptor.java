package xyz.gabear.learn.ssm.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(ApiInterceptor.class);

    // 返回值表示是请求是否继续
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle");
        // 通过拦截器也可以设置编码
        /*request.setCharacterEncoding("UTF-8");
        if (request.getSession().getAttribute("user") == null) {
            request.getRequestDispatcher("/department/show/1").forward(request, response);
            return false;
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在控制器中处理完之后、返回视图之前，在这里可以做一些操作
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 关闭一些资源
    }
}

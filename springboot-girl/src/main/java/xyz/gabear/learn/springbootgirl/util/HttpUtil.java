package xyz.gabear.learn.springbootgirl.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HttpUtil {

    /**
     * 判断是否是ajax请求
     *
     * @param httpRequest 请求体
     * @return true or false
     */
    public static boolean isAjax(HttpServletRequest httpRequest) {
        return (httpRequest.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With")));
    }
}

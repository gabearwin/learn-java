package xyz.gabear.learn.springbootgirl.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import xyz.gabear.learn.springbootgirl.util.JsonDTO;
import xyz.gabear.learn.springbootgirl.util.HttpUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = GirlException.class)
    public JsonDTO girlExceptionHandler(GirlException e) {
        log.error(e.getMessage(), e);
        return JsonDTO.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error(e.getMessage(), e);
        if (HttpUtil.isAjax(request)) {
            // 若是Ajax请求，则返回json错误信息
            return JsonDTO.fail(-1, e.getMessage());
        } else {
            // 若是页面请求，则跳转到错误页面
            ModelAndView mav = new ModelAndView("error/error");
            mav.addObject("exception", e.getMessage());
            mav.addObject("url", request.getRequestURL());
            return mav;
        }
    }

}

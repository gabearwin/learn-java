package xyz.gabear.learn.ssm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.gabear.learn.ssm.dto.CommonRet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class BaseController {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    private HttpServletRequest request;
    private HttpServletResponse response;

    @ModelAttribute
    public void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public CommonRet handleIllegalArgumentException(Exception e) {
        logger.warn(e.getMessage(), e);
        CommonRet ret = new CommonRet();
        ret.setCode(CommonRet.ERROR);
        ret.setMsg("参数错误：" + e.getMessage());
        return ret;
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public CommonRet handleException(BindException e) {
        logger.warn(e.getMessage(), e);
        CommonRet ret = new CommonRet();
        FieldError fieldError = e.getFieldError();
        String msg = "数据异常，字段 " + fieldError.getField() + " 填写的值 " + fieldError.getRejectedValue().toString() + " 类型错误";
        ret.setCode(CommonRet.ERROR);
        ret.setMsg(msg);
        return ret;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonRet handleException(Exception e) {
        logger.warn(e.getMessage(), e);
        CommonRet ret = new CommonRet();
        ret.setCode(CommonRet.ERROR);
        ret.setMsg("操作失败：" + e.getMessage());
        return ret;
    }

    /**
     * This function is used to make @ModelAttribute successfully inject into a bean which having java.util.Date fields.
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"), true);
        binder.registerCustomEditor(Date.class, editor);
    }
}

package xyz.gabear.learn.ssm.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 返回到前端的通用封装类
 */
public class CommonRet {
    private String code;
    private String msg;

    public final static String SUCCESS = "A00000";
    public final static String SUCCESS_MSG = "success";
    public final static String ERROR = "A00001";
    public final static String PARTIAL_SUCCESS = "A00002";
    public final static String ERROR_MSG = "general internal error";

    public CommonRet() {
        this(SUCCESS, SUCCESS_MSG);
    }

    public CommonRet(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void error(String msg) {
        this.code = ERROR;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return SUCCESS.equals(code);
    }

    @JsonIgnore
    public boolean isFailed() {
        return !SUCCESS.equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

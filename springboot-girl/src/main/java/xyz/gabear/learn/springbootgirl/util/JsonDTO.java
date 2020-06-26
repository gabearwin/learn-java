package xyz.gabear.learn.springbootgirl.util;

import xyz.gabear.learn.springbootgirl.enums.ResultEnum;

public class JsonDTO<T> {
    private Integer code;
    private Boolean success;
    private String msg;
    private T data;

    private JsonDTO(Integer code, Boolean success, String msg, T data) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 请求失败
     *
     * @param code 失败状态码
     * @param msg  失败错误信息
     */
    public static JsonDTO fail(Integer code, String msg) {
        return new JsonDTO(code, false, msg, null);
    }

    /**
     * 请求失败
     *
     * @param resultEnum 失败信息
     */
    public static JsonDTO fail(ResultEnum resultEnum) {
        return new JsonDTO(resultEnum.getCode(), false, resultEnum.getMsg(), null);
    }

    /**
     * 请求成功
     */
    public static JsonDTO success() {
        return new JsonDTO(null, true, null, null);
    }

    /**
     * 请求成功
     *
     * @param data 成功时返回的数据
     */
    public static <T> JsonDTO success(T data) {
        return new JsonDTO(null, true, null, data);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonDTO{" +
                "code=" + code +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

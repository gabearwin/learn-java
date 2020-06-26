package xyz.gabear.learn.springbootgirl.enums;

public enum ResultEnum {
    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(0, "请求成功"),
    A_ERROR(-1, "你还在上小学吧"),
    B_ERROR(-1, "你还在上初中吧"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

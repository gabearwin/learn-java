package xyz.gabear.learn.springbootgirl.exception;

import xyz.gabear.learn.springbootgirl.enums.ResultEnum;

public class GirlException extends RuntimeException {
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public GirlException(String message) {
        super(message);
    }

    public GirlException(String message, Throwable cause) {
        super(message, cause);
    }

    public GirlException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public GirlException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

}

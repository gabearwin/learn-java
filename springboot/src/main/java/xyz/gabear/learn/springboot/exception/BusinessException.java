package xyz.gabear.learn.springboot.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BusinessException extends RuntimeException {
    private int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }
}

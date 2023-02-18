package xyz.gabear.learn.springboot.exception;

import lombok.Getter;

/**
 * 类名称：BusinessException
 * ********************************
 * <p>
 * 类描述：业务类异常
 *
 * @author
 * @date 下午9:34
 */
public class BusinessException extends RuntimeException {

    /**
     * 异常编号
     */
    @Getter
    private final String code;

    /**
     * 根据枚举构造业务类异常
     * @param error
     */
    public BusinessException(ErrorCodeEnum error) {
        super(error.getMessage());
        this.code = error.getCode();
    }

    /**
     * 自定义消息体构造业务类异常
     * @param error
     * @param message
     */
    public BusinessException(ErrorCodeEnum error, String message) {
        super(message);
        this.code = error.getCode();
    }

    /**
     * 根据异常构造业务类异常
     * @param error
     * @param cause
     */
    public BusinessException(ErrorCodeEnum error, Throwable cause) {
        super(cause);
        this.code = error.getCode();
    }

}

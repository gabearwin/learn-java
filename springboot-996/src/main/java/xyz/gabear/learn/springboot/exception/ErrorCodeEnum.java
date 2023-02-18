package xyz.gabear.learn.springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 类名称：ErrorCodeEnum
 * ********************************
 * <p>
 * 类描述：异常编码枚举
 *
 * @author
 * @date 2020/3/1 下午9:54
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    // 0*** 成功
    SUCCESS("0000", "操作成功"),

    // 1*** 参数异常
    PARAM_ERROR("1001", "参数异常"),
    PARAM_NULL("1002", "参数为空"),
    PARAM_FORMAT_ERROR("1003", "参数格式不正确"),
    PARAM_VALUE_ERROR("1004", "参数值不正确"),

    // 2*** 系统异常
    SYSTEM_ERROR("2001", "服务异常"),
    UNKNOWN_ERROR("2002", "未知异常"),

    // 3*** 业务异常
    XXX("3001", "业务异常"),
    INSERT_FAILURE("3002", "新增失败"),
    UPDATE_FAILURE("3003", "更新失败"),
    DELETE_FAILURE("3004", "删除失败"),
    RATE_LIMIT_ERROR("3005", "限流异常"),
    FILE_UPLOAD_FAILURE("3006", "文件上传失败"),

    ;


    /**
     * 错误编码
     */
    private String code;

    /**
     * 错误描述
     */
    private String message;


}

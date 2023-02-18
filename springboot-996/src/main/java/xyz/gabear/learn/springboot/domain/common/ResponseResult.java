package xyz.gabear.learn.springboot.domain.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import xyz.gabear.learn.springboot.exception.ErrorCodeEnum;

import java.io.Serializable;

/**
 * 类名称：ResponseResult
 * ********************************
 * <p>
 * 类描述：通用返回结果模型
 *
 * @author
 * @date 下午12:59
 */
@Data
@ApiModel(
        value = "统一返回结果实体",
        description = "封装统一返回结果信息实体"
)
public class ResponseResult<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7813356989387725160L;

    /**
     * 是否成功
     */
    @ApiModelProperty(
            name = "success",
            value = "是否成功",
            required = true,
            dataType = "Boolean"
    )
    private Boolean success;

    /**
     * 编码
     */
    @ApiModelProperty(
            name = "code",
            value = "编码",
            required = false,
            dataType = "String"
    )
    private String code;

    /**
     * 描述信息
     */
    @ApiModelProperty(
            value = "描述信息"
    )
    private String message;

    /**
     * 结果
     */
    @ApiModelProperty(
            value = "泛型结果T"
    )
    private T result;

    /**
     * 成功
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(T result) {
        ResponseResult<T> responseResult = new ResponseResult<>();

        responseResult.setSuccess(Boolean.TRUE);
        responseResult.setResult(result);

        return responseResult;
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> failure(String code, String message) {
        ResponseResult<T> responseResult = new ResponseResult<>();

        responseResult.setSuccess(Boolean.FALSE);
        responseResult.setCode(code);
        responseResult.setMessage(message);

        return responseResult;
    }

    /**
     * 失败
     *
     * @param codeEnum
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> failure(ErrorCodeEnum codeEnum) {
        return failure(codeEnum.getCode(), codeEnum.getMessage());
    }
}

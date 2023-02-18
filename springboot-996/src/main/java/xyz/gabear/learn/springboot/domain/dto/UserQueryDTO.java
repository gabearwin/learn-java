package xyz.gabear.learn.springboot.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 类名称：UserQueryDTO
 * ********************************
 * <p>
 * 类描述：数据查询DTO实体
 *
 * @author
 * @date 下午1:14
 */
@Data
public class UserQueryDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6388963865596623280L;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户姓名不能为空！")
    private String username;
}

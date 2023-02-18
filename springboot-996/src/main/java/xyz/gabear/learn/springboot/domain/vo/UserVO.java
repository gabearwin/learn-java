package xyz.gabear.learn.springboot.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 类名称：UserVO
 * ********************************
 * <p>
 * 类描述：用户VO实体
 *
 * @author
 * @date 下午12:46
 */
@Data
public class UserVO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4669154981420690987L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机号
     */
    private String phone;
}

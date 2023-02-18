package xyz.gabear.learn.springboot.domain.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 类名称：PageResult
 * ********************************
 * <p>
 * 类描述：通用分页查询返回实体
 *
 * @author
 * @date 下午1:17
 */
@Data
public class PageResult<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5074000774435553698L;

    /**
     * 当前页号
     */
    private Integer pageNo;

    /**
     * 每页行数
     */
    private Integer pageSize;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long pageNum;

    /**
     * 动态的内容
     */
    private T data;
}

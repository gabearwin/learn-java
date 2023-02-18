package xyz.gabear.learn.springboot.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import xyz.gabear.learn.springboot.util.LocalDateTimeStringConverter;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 类名称：UserExportDTO
 * ********************************
 * <p>
 * 类描述：Excel导出实体对象
 *
 * @author
 * @date 上午10:54
 */
@Data
public class UserExportDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1933919487755303928L;

    /**
     * String 类型
     */
    @ExcelProperty(value = "用户名")
    private String username;

    /**
     * Integer 类型
     */
    @ExcelProperty(value = "年龄")
    private Integer age;

    /**
     * Long 类型
     */
    @ExcelProperty(value = "版本号")
    private Long version;

    /**
     * LocalDateTime 类型
     */
    @ExcelProperty(value = "创建时间",
            converter = LocalDateTimeStringConverter.class)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒SSS毫秒")
    private LocalDateTime created;
}

package xyz.gabear.springbootflow.db;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @date 2021/7/16
 */
@Data
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
}

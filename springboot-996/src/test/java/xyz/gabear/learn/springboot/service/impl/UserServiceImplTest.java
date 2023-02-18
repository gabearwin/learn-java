package xyz.gabear.learn.springboot.service.impl;

import xyz.gabear.learn.springboot.domain.dto.UserDTO;
import xyz.gabear.learn.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 类名称：UserServiceImplTest
 * ********************************
 * <p>
 * 类描述：
 *
 * @author
 * @date 下午2:44
 */
@SpringBootTest
@Slf4j
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void saveTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("username1");
        userDTO.setPassword("password1");
        userDTO.setEmail("email@email.com");
        userDTO.setAge(1);
        userDTO.setPhone("15011110000");
        userDTO.setVersion(1L);

        int save = userService.save(userDTO);

        log.info("{}", save);
    }

    /**
     * 乐观锁使用的规则
     * 1. 如果更新数据中不带有version字段：不使用乐观锁，并且version不会累加
     * 2. 如果更新字段中带有version，但与数据库中不一致，更新失败
     * 3. 如果带有version，并且与数据库中一致，更新成功，并且version会累加
     */
    @Test
    public void updateTest() {

        Long id = 1234012634597076993L;

        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("password10000");
        userDTO.setAge(10000);
        userDTO.setVersion(1L);

        int update = userService.update(id, userDTO);

        log.info("{}", update);
    }

    @Test
    public void deleteTest() {
        int delete = userService.delete(1234012634597076993L);

        log.info("{}", delete);
    }

}

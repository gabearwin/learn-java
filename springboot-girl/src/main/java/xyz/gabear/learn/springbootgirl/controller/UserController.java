package xyz.gabear.learn.springbootgirl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.gabear.learn.springbootgirl.domain.User;

import java.util.Date;

@RestController
public class UserController {

    @RequestMapping("/get")
    public User getUser() {
        User user = new User();
        user.setName("gao");
        user.setPassword("8888888");
        user.setAge(18);
        user.setBirthday(new Date());
        return user;
    }

}

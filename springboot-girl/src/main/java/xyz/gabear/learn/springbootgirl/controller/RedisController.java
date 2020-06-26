package xyz.gabear.learn.springbootgirl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.gabear.learn.springbootgirl.domain.User;
import xyz.gabear.learn.springbootgirl.domain.entity.gen.SysUser;
import xyz.gabear.learn.springbootgirl.util.JsonDTO;
import xyz.gabear.learn.springbootgirl.util.JsonUtils;
import xyz.gabear.learn.springbootgirl.util.RedisOperator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate strRedis;

    @Autowired
    private RedisOperator redis;

    @RequestMapping("/test")
    public JsonDTO test() {

        strRedis.opsForValue().set("imooc-cache", "hello 慕课网~~~~~~");

        SysUser user = new SysUser();
        user.setId("100111");
        user.setUsername("imooc");
        user.setPassword("abc123");
        user.setIsDelete(0);
        user.setRegistTime(new Date());
        strRedis.opsForValue().set("json:user", JsonUtils.objectToJson(user));

        SysUser jsonUser = JsonUtils.jsonToPojo(strRedis.opsForValue().get("json:user"), SysUser.class);

        return JsonDTO.success(jsonUser);
    }

    @RequestMapping("/getJsonList")
    public JsonDTO getJsonList() {

        User u0 = new User();
        u0.setAge(18);
        u0.setName("慕课网");
        u0.setPassword("123456");
        u0.setBirthday(new Date());

        User u1 = new User();
        u1.setAge(19);
        u1.setName("imooc");
        u1.setPassword("123456");
        u1.setBirthday(new Date());

        User u2 = new User();
        u2.setAge(17);
        u2.setName("hello imooc");
        u2.setPassword("123456");
        u2.setBirthday(new Date());

        List<User> userList = new ArrayList<>();
        userList.add(u0);
        userList.add(u1);
        userList.add(u2);

        redis.set("json:info:userlist", JsonUtils.objectToJson(userList), 2000);

        String userListJson = redis.get("json:info:userlist");
        List<User> userListBorn = JsonUtils.jsonToList(userListJson, User.class);

        return JsonDTO.success(userListBorn);
    }
}
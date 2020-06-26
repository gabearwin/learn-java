package xyz.gabear.learn.springbootgirl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.gabear.learn.springbootgirl.domain.entity.gen.SysUser;
import xyz.gabear.learn.springbootgirl.service.SysUserService;
import xyz.gabear.learn.springbootgirl.util.JsonDTO;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/mybatis")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    /*@Autowired
    private Sid sid;*/

    @RequestMapping("/saveUser")
    public JsonDTO saveUser() throws Exception {

        // String userId = sid.nextShort();
        String userId = "abc";

        SysUser user = new SysUser();
        user.setId(userId);
        user.setUsername("imooc");
        user.setNickname("imooc");
        user.setPassword("abc123");
        user.setIsDelete(0);
        user.setRegistTime(new Date());

        userService.saveUser(user);

        return JsonDTO.success("保存成功");
    }

    @RequestMapping("/updateUser")
    public JsonDTO updateUser() {

        SysUser user = new SysUser();
        user.setId("10011001");
        user.setUsername("123-updated");
        user.setNickname("123-updated");
        user.setPassword("123-updated");
        user.setIsDelete(0);
        user.setRegistTime(new Date());

        userService.updateUser(user);

        return JsonDTO.success("保存成功");
    }

    @RequestMapping("/deleteUser")
    public JsonDTO deleteUser(String userId) {

        userService.deleteUser(userId);

        return JsonDTO.success("删除成功");
    }

    @RequestMapping("/queryUserById")
    public JsonDTO queryUserById(String userId) {

        return JsonDTO.success(userService.queryUserById(userId));
    }

    @RequestMapping("/queryUserList")
    public JsonDTO queryUserList() {

        SysUser user = new SysUser();
        user.setUsername("imooc");
        user.setNickname("lee");

        List<SysUser> userList = userService.queryUserList(user);

        return JsonDTO.success(userList);
    }

    @RequestMapping("/queryUserListPaged")
    public JsonDTO queryUserListPaged(Integer page) {

        if (page == null) {
            page = 1;
        }

        int pageSize = 2;

        SysUser user = new SysUser();
//		user.setNickname("lee");

        List<SysUser> userList = userService.queryUserListPaged(user, page, pageSize);

        return JsonDTO.success(userList);
    }

    @RequestMapping("/queryUserByIdCustom")
    public JsonDTO queryUserByIdCustom(String userId) {

        return JsonDTO.success(userService.queryUserByIdCustom(userId));
    }

    @RequestMapping("/saveUserTransactional")
    public JsonDTO saveUserTransactional() {

        // String userId = sid.nextShort();
        String userId = "abcdefg";

        SysUser user = new SysUser();
        user.setId(userId);
        user.setUsername("lee" + new Date());
        user.setNickname("lee" + new Date());
        user.setPassword("abc123");
        user.setIsDelete(0);
        user.setRegistTime(new Date());

        userService.saveUserTransactional(user);

        return JsonDTO.success("保存成功");
    }
}
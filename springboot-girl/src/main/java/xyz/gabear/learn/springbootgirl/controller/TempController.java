package xyz.gabear.learn.springbootgirl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.gabear.learn.springbootgirl.domain.User;

import java.util.Date;

@Controller
@RequestMapping("/show")
public class TempController {

    @RequestMapping("/html/1")
    public String test11() {
        return "th1";
    }

    @RequestMapping("/html/2")
    public String test12(ModelMap modelMap) {
        modelMap.addAttribute("name", "thymeleaf");
        return "th1";
    }

    @RequestMapping("/html/3")
    public String test13(ModelMap modelMap) {
        User user = new User();
        user.setAge(20);
        user.setName("yan");
        user.setBirthday(new Date());
        modelMap.addAttribute("user", user);
        return "test";
    }

    @RequestMapping("/ftl/1")
    public String test21() {
        return "ftl1";
    }

    @RequestMapping("/ftl/2")
    public String test22(ModelMap modelMap) {
        modelMap.addAttribute("name", "freemarker");
        modelMap.addAttribute("user", new User());
        return "ftl1";
    }

    @RequestMapping("/ftl/3")
    public String test23(ModelMap modelMap) {
        int a = 1 / 0;
        return "ftl1";
    }

}

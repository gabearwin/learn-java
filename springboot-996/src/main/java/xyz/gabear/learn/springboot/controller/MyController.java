package xyz.gabear.learn.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/rest")
public class MyController {

    @RequestMapping("/1")
    @ResponseBody
    public String hello1() {
        return "hello";
    }

    @RequestMapping("/2")
    public String hello2() {
        return "redirect:/rest/1";
    }
}

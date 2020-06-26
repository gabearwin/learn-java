package xyz.gabear.learn.springbootgirl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class TestController {

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1() {
        return "test1";
    }

    @RequestMapping(value = "/test2/{id}", method = RequestMethod.GET)
    public String test2(@PathVariable("id") Integer myId) {
        return "id=" + myId;
    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public String test3(@RequestParam("id") Integer id) {
        return "id=" + id;
    }

    @GetMapping(value = "/test4")
    public String test4(@RequestParam("id") Integer id) {
        return "id=" + id;
    }

    @GetMapping(value = "/test5")
    public String test5(@RequestParam(value = "id", required = false, defaultValue = "888") Integer id) {
        return "id=" + id;
    }

    // @RequestParam还可以绑定List、Map
    // @ModelAttribute可以绑定自定义Bean
}

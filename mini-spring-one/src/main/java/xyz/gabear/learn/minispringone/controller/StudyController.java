package xyz.gabear.learn.minispringone.controller;

import xyz.gabear.learn.minispringone.annotation.Controller;
import xyz.gabear.learn.minispringone.annotation.Qualifier;
import xyz.gabear.learn.minispringone.annotation.RequestMapping;
import xyz.gabear.learn.minispringone.service.StudyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/controller")
public class StudyController {

    @Qualifier("studyService")
    private StudyService studyService;

    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response, String param) {
        System.out.println(param);

        studyService.insert(null);
        studyService.delete(null);
        studyService.update(null);
        studyService.select(null);

        try {
            response.getWriter().println("do println:" + param);
            response.getWriter().write("do write:" + param);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("Hello world!");
        return "Hello world!";
    }

}

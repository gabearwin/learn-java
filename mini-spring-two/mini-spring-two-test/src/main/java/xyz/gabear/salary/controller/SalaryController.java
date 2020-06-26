package xyz.gabear.salary.controller;


import xyz.gabear.salary.service.SalaryService;
import xyz.gabear.spring.beans.Autowired;
import xyz.gabear.spring.web.mvc.Controller;
import xyz.gabear.spring.web.mvc.RequestMapping;
import xyz.gabear.spring.web.mvc.RequestParam;

@Controller
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @RequestMapping("/get_salary.json")
    public Integer getSalary(@RequestParam("name") String name,
                             @RequestParam("experience") String experience) {
        System.out.println(String.format("name=%s,experience=%s", name, experience));
        return salaryService.calSalary(Integer.parseInt(experience));
    }

}

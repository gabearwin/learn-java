package xyz.gabear.learn.ssm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.gabear.learn.ssm.service.DepartmentService;
import xyz.gabear.learn.ssm.dao.entity.gen.Department;
import xyz.gabear.learn.ssm.exception.AuthenticateException;

@RequestMapping("/department")
@Controller
public class DepartmentController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentService service;

    @RequestMapping("/show/{id}")
    @ResponseBody
    public Department getById(@PathVariable long id) {
        if (id == 1) {
            logger.error("验证错误");
            throw new AuthenticateException("验证错误");
        }
        return service.selectByPK(id);
    }

}

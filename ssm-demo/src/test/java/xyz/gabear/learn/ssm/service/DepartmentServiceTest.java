package xyz.gabear.learn.ssm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.gabear.learn.ssm.dao.entity.gen.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class DepartmentServiceTest {
    @Autowired
    private DepartmentService service;

    @Test
    public void selectByPK() {
        Department department = service.selectByPK(1);
        System.out.println(department.getCnName());
    }
}
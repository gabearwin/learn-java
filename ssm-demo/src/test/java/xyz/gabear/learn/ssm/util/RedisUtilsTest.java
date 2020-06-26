package xyz.gabear.learn.ssm.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.gabear.learn.ssm.dao.entity.gen.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class RedisUtilsTest {
    private final static Logger logger = LoggerFactory.getLogger(RedisUtilsTest.class);

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void test() {
        String result = redisUtils.set("name", "gabear");
        logger.warn(result);
        result = redisUtils.get("name");
        logger.warn(result);
    }

    @Test
    public void test1() {
        Department department = new Department();
        department.setCnName("技术产品中心");
        department.setId(11L);
        String result = redisUtils.setObj("gabear", department);
        logger.warn(result);
        Department newDepart = redisUtils.getObj("gabear", Department.class);
        logger.warn(newDepart.getCnName());
    }
}
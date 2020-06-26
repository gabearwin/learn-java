package xyz.gabear.learn.springbootgirl.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.gabear.learn.springbootgirl.domain.Girl;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {
    @Autowired
    private GirlService girlService;

    @Test
    public void insertTwo() {
    }

    @Test
    public void getAge() {
    }

    @Test
    public void findById() {
        Optional<Girl> girl = girlService.findById(15);
        Assert.assertEquals(new Integer(10), girl.get().getAge());
    }
}
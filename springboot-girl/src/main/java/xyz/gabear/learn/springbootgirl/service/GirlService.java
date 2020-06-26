package xyz.gabear.learn.springbootgirl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.gabear.learn.springbootgirl.domain.Girl;
import xyz.gabear.learn.springbootgirl.enums.ResultEnum;
import xyz.gabear.learn.springbootgirl.exception.GirlException;
import xyz.gabear.learn.springbootgirl.repository.GirlRepository;

import java.util.Optional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    // 注意设置数据库表引擎为InnoDB才支持事物
    @Transactional
    public String insertTwo() {
        Girl girl1 = new Girl();
        girl1.setAge(111);
        girl1.setName("aaa");
        girlRepository.save(girl1);

        if (girl1.getAge() == 111) {
            throw new GirlException(ResultEnum.UNKNOWN_ERROR);
        }

        Girl girl2 = new Girl();
        girl2.setAge(222);
        girl2.setName("bbb");
        girlRepository.save(girl2);

        return "success";
    }

    public void getAge(Integer id) {
        Girl girl = girlRepository.findById(id).orElseThrow(RuntimeException::new);
        Integer age = girl.getAge();
        if (age < 12) {
            throw new GirlException(ResultEnum.A_ERROR);
        } else if (age < 16) {
            throw new GirlException(ResultEnum.B_ERROR);
        }
    }

    public Optional<Girl> findById(Integer id){
        return girlRepository.findById(id);
    }
}

package xyz.gabear.learn.minispringone.service.impl;

import xyz.gabear.learn.minispringone.annotation.Service;
import xyz.gabear.learn.minispringone.service.StudyService;

import java.util.Map;

@Service("studyService")
public class StudyServiceImpl implements StudyService {
    @Override
    public Integer insert(Map map) {
        System.out.println("调用了 StudyServiceImpl 类中的 insert 方法");
        return 0;
    }

    @Override
    public Integer delete(Map map) {
        System.out.println("调用了 StudyServiceImpl 类中的 delete 方法");
        return 0;
    }

    @Override
    public Integer update(Map map) {
        System.out.println("调用了 StudyServiceImpl 类中的 update 方法");
        return 0;
    }

    @Override
    public Integer select(Map map) {
        System.out.println("调用了 StudyServiceImpl 类中的 select 方法");
        return 0;
    }
}

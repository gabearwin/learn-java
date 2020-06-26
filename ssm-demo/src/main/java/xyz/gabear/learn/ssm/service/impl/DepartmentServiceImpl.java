package xyz.gabear.learn.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.gabear.learn.ssm.dao.mapper.gen.DepartmentMapper;
import xyz.gabear.learn.ssm.service.DepartmentService;
import xyz.gabear.learn.ssm.dao.entity.gen.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper mapper;

    @Override
    public Department selectByPK(long id) {
        return mapper.selectByPrimaryKey(id);
    }
}

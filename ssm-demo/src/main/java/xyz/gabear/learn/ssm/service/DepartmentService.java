package xyz.gabear.learn.ssm.service;

import xyz.gabear.learn.ssm.dao.entity.gen.Department;

public interface DepartmentService {
    Department selectByPK(long id);
}

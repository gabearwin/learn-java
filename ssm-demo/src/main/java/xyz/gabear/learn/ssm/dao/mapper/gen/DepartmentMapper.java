package xyz.gabear.learn.ssm.dao.mapper.gen;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.gabear.learn.ssm.dao.mapper.BaseMapper;
import xyz.gabear.learn.ssm.dao.entity.gen.Department;
import xyz.gabear.learn.ssm.dao.entity.gen.DepartmentExample;

public interface DepartmentMapper extends BaseMapper {
    long countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(DepartmentExample example);

    Department selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}
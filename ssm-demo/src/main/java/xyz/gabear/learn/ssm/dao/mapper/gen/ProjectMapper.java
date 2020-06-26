package xyz.gabear.learn.ssm.dao.mapper.gen;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.gabear.learn.ssm.dao.mapper.BaseMapper;
import xyz.gabear.learn.ssm.dao.entity.gen.Project;
import xyz.gabear.learn.ssm.dao.entity.gen.ProjectExample;

public interface ProjectMapper extends BaseMapper {
    long countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExample(ProjectExample example);

    Project selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
}
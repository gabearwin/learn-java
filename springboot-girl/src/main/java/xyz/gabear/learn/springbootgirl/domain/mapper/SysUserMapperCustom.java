package xyz.gabear.learn.springbootgirl.domain.mapper;

import xyz.gabear.learn.springbootgirl.domain.entity.gen.SysUser;
import xyz.gabear.learn.springbootgirl.util.MyMapper;

import java.util.List;

public interface SysUserMapperCustom extends MyMapper<SysUser> {
    List<SysUser> queryUserSimplyInfoById(String id);
}
package xyz.gabear.learn.springbootgirl.service;

import xyz.gabear.learn.springbootgirl.domain.entity.gen.SysUser;

import java.util.List;

public interface SysUserService {

    public void saveUser(SysUser user) throws Exception;

    public void updateUser(SysUser user);

    public void deleteUser(String userId);

    public SysUser queryUserById(String userId);

    public List<SysUser> queryUserList(SysUser user);

    public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize);

    public SysUser queryUserByIdCustom(String userId);

    public void saveUserTransactional(SysUser user);
}

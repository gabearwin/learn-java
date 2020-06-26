package xyz.gabear.learn.springbootgirl.domain.entity.gen;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser {
    /**
     * 主键ID，自增长ID
     */
    private String id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册邮箱地址
     */
    private String nickname;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别：0保密，1男性，2女性
     */
    private Integer sex;

    /**
     * 工作
     */
    private Integer job;

    /**
     * 头像
     */
    @Column(name = "face_image")
    private String faceImage;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 地址
     */
    private String address;

    /**
     * 认证
     */
    @Column(name = "auth_salt")
    private String authSalt;

    /**
     * 登录IP
     */
    @Column(name = "last_login_ip")
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 是否删除
     */
    @Column(name = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @Column(name = "regist_time")
    private Date registTime;

    @Column(name = "user_uuid")
    private String userUuid;

    /**
     * 获取主键ID，自增长ID
     *
     * @return id - 主键ID，自增长ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键ID，自增长ID
     *
     * @param id 主键ID，自增长ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取登录账号
     *
     * @return username - 登录账号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置登录账号
     *
     * @param username 登录账号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取注册邮箱地址
     *
     * @return nickname - 注册邮箱地址
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置注册邮箱地址
     *
     * @param nickname 注册邮箱地址
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取性别：0保密，1男性，2女性
     *
     * @return sex - 性别：0保密，1男性，2女性
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别：0保密，1男性，2女性
     *
     * @param sex 性别：0保密，1男性，2女性
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取工作
     *
     * @return job - 工作
     */
    public Integer getJob() {
        return job;
    }

    /**
     * 设置工作
     *
     * @param job 工作
     */
    public void setJob(Integer job) {
        this.job = job;
    }

    /**
     * 获取头像
     *
     * @return face_image - 头像
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * 设置头像
     *
     * @param faceImage 头像
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区
     *
     * @return district - 区
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置区
     *
     * @param district 区
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取认证
     *
     * @return auth_salt - 认证
     */
    public String getAuthSalt() {
        return authSalt;
    }

    /**
     * 设置认证
     *
     * @param authSalt 认证
     */
    public void setAuthSalt(String authSalt) {
        this.authSalt = authSalt;
    }

    /**
     * 获取登录IP
     *
     * @return last_login_ip - 登录IP
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * 设置登录IP
     *
     * @param lastLoginIp 登录IP
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * 获取最后登录时间
     *
     * @return last_login_time - 最后登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastLoginTime 最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取是否删除
     *
     * @return is_delete - 是否删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除
     *
     * @param isDelete 是否删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取创建时间
     *
     * @return regist_time - 创建时间
     */
    public Date getRegistTime() {
        return registTime;
    }

    /**
     * 设置创建时间
     *
     * @param registTime 创建时间
     */
    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    /**
     * @return user_uuid
     */
    public String getUserUuid() {
        return userUuid;
    }

    /**
     * @param userUuid
     */
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }
}
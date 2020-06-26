package xyz.gabear.learn.springbootgirl.domain.entity.gen;

import javax.persistence.*;

public class Project {
    @Id
    private Long id;

    /**
     * 项目编号
     */
    private Integer pid;

    /**
     * 项目英文名
     */
    @Column(name = "en_name")
    private String enName;

    /**
     * 项目中文名
     */
    @Column(name = "cn_name")
    private String cnName;

    /**
     * 描述信息
     */
    private String description;

    @Column(name = "owner_id")
    private Integer ownerId;

    /**
     * 项目所在部门ID
     */
    @Column(name = "department_id")
    private Integer departmentId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取项目编号
     *
     * @return pid - 项目编号
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置项目编号
     *
     * @param pid 项目编号
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取项目英文名
     *
     * @return en_name - 项目英文名
     */
    public String getEnName() {
        return enName;
    }

    /**
     * 设置项目英文名
     *
     * @param enName 项目英文名
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /**
     * 获取项目中文名
     *
     * @return cn_name - 项目中文名
     */
    public String getCnName() {
        return cnName;
    }

    /**
     * 设置项目中文名
     *
     * @param cnName 项目中文名
     */
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    /**
     * 获取描述信息
     *
     * @return description - 描述信息
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述信息
     *
     * @param description 描述信息
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return owner_id
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 获取项目所在部门ID
     *
     * @return department_id - 项目所在部门ID
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置项目所在部门ID
     *
     * @param departmentId 项目所在部门ID
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
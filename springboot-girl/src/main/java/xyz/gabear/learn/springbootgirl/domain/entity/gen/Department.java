package xyz.gabear.learn.springbootgirl.domain.entity.gen;

import java.util.Date;
import javax.persistence.*;

public class Department {
    @Id
    private Long id;

    /**
     * 部门编号
     */
    private Integer did;

    /**
     * 上级部门编号
     */
    @Column(name = "parent_did")
    private Integer parentDid;

    /**
     * 部门中文名称
     */
    @Column(name = "cn_name")
    private String cnName;

    /**
     * 部门完整层级名称
     */
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modified")
    private Date gmtModified;

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
     * 获取部门编号
     *
     * @return did - 部门编号
     */
    public Integer getDid() {
        return did;
    }

    /**
     * 设置部门编号
     *
     * @param did 部门编号
     */
    public void setDid(Integer did) {
        this.did = did;
    }

    /**
     * 获取上级部门编号
     *
     * @return parent_did - 上级部门编号
     */
    public Integer getParentDid() {
        return parentDid;
    }

    /**
     * 设置上级部门编号
     *
     * @param parentDid 上级部门编号
     */
    public void setParentDid(Integer parentDid) {
        this.parentDid = parentDid;
    }

    /**
     * 获取部门中文名称
     *
     * @return cn_name - 部门中文名称
     */
    public String getCnName() {
        return cnName;
    }

    /**
     * 设置部门中文名称
     *
     * @param cnName 部门中文名称
     */
    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    /**
     * 获取部门完整层级名称
     *
     * @return full_name - 部门完整层级名称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置部门完整层级名称
     *
     * @param fullName 部门完整层级名称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * @param gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
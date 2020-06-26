package xyz.gabear.learn.springbootgirl.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class User {
    private String name;

    @JsonIgnore // 返回Json时，该字段不显示
    private String password;

    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a", locale = "zh", timezone = "GMT+8")
    private Date birthday; // 返回Json时，对该字段进行格式化

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String desc; // 返回Json时，若该字段为空，则不显示。显示策略是枚举类，可以选择其他方式。

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

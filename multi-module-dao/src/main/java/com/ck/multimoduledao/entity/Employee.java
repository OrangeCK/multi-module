package com.ck.multimoduledao.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ck
 * @date 2018/10/17 15:46
 * Description  :
 */
public class Employee extends BaseForm{
    private Long id;
    /**
     * 登录名称
     */
    private String loginName;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户电话
     */
    private String userPhone;
    /**
     * 工号
     */
    private String jobNumber;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别代码
     */
    private String sex;
    /**
     * 性别名称
     */
    private String sexName;
    /**
     * 角色
     */
    private List<Role> roles = new ArrayList<>();

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", sexName='" + sexName + '\'' +
                ", roles=" + roles +
                '}';
    }
}

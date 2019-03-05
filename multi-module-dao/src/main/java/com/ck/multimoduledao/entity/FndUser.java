package com.ck.multimoduledao.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName(value="m_fnd_user")
public class FndUser{
	/*  */
    private Long id;

	/*  */
    private String loginName;

	/*  */
    private String userName;

	/*  */
    private String password;

	/*  */
    private String jobNumber;

	/*  */
    private int age;

	/*  */
    private String sex;

	/*  */
    private String userPhone;

	/*  */
    private String enableFlag;

	/*  */
    private Long sCid;

	/*  */
    private Date sCt;

	/*  */
    private Long sUid;

	/*  */
    private Date sUt;

    public Long getId(){

        return this.id;
    }
    public void setId(Long id){

        this.id = id;
    }
    public String getLoginName(){

        return this.loginName;
    }
    public void setLoginName(String loginName){

        this.loginName = loginName;
    }
    public String getUserName(){

        return this.userName;
    }
    public void setUserName(String userName){

        this.userName = userName;
    }
    public String getPassword(){

        return this.password;
    }
    public void setPassword(String password){

        this.password = password;
    }
    public String getJobNumber(){

        return this.jobNumber;
    }
    public void setJobNumber(String jobNumber){

        this.jobNumber = jobNumber;
    }
    public int getAge(){

        return this.age;
    }
    public void setAge(int age){

        this.age = age;
    }
    public String getSex(){

        return this.sex;
    }
    public void setSex(String sex){

        this.sex = sex;
    }
    public String getUserPhone(){

        return this.userPhone;
    }
    public void setUserPhone(String userPhone){

        this.userPhone = userPhone;
    }
    public String getEnableFlag(){

        return this.enableFlag;
    }
    public void setEnableFlag(String enableFlag){

        this.enableFlag = enableFlag;
    }
    public Long getSCid(){

        return this.sCid;
    }
    public void setSCid(Long sCid){

        this.sCid = sCid;
    }
    public Date getSCt(){

        return this.sCt;
    }
    public void setSCt(Date sCt){

        this.sCt = sCt;
    }
    public Long getSUid(){

        return this.sUid;
    }
    public void setSUid(Long sUid){

        this.sUid = sUid;
    }
    public Date getSUt(){

        return this.sUt;
    }
    public void setSUt(Date sUt){

        this.sUt = sUt;
    }
}

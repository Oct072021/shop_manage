package com.separate.fruit.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * users
 *
 * @author
 */
public class Users implements Serializable {

    private Integer userId;//用户的id
    private String userName;//用户名
    private String phone;//用户手机号码
    private String password;//用户密码
    private String userEmail;//用户邮箱,用于找回密码
    private Date signInData;//注册时间
    private String token;//设置token过期时间

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getSignInData() {
        return signInData;
    }

    public void setSignInData(Date signInData) {
        this.signInData = signInData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
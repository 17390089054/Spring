package com.wrf.user.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

@Alias("u")
public class User {
	private Integer userId;
	private Integer userAge;
	private String userName;
	private String userSex;
	private String account;
	private String password;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date userCreateTime;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getUserCreateTime() {
		return userCreateTime;
	}
	public void setUserCreateTime(Date userCreateTime) {
		this.userCreateTime = userCreateTime;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userAge=" + userAge + ", userName=" + userName + ", userSex=" + userSex
				+ ", account=" + account + ", password=" + password + ", userCreateTime=" + userCreateTime + "]";
	}
	
	
	
}

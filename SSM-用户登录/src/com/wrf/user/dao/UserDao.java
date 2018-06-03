package com.wrf.user.dao;

import org.apache.ibatis.annotations.Param;

import com.wrf.user.model.User;

public interface UserDao {
	public User queryUser(@Param("account")String account,@Param("password")String password);
}

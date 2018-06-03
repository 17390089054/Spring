package com.wrf.service.impl;

import com.wrf.dao.UserDao;
import com.wrf.service.UserService;

public class UserServiceImpl implements UserService{
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void login() {
		userDao.login();
	}



}

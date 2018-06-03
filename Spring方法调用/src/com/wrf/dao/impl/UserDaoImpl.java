package com.wrf.dao.impl;

import com.wrf.dao.UserDao;

public class UserDaoImpl implements UserDao {

	@Override
	public void login() {
		System.out.println("====MyDao 欢迎登陆====");
	}

}

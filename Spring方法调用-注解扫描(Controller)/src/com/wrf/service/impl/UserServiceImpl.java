package com.wrf.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.wrf.dao.UserDao;
import com.wrf.service.UserService;

//当前bean的id 默认类名小写首字母
//@Component
@Service
public class UserServiceImpl implements UserService{
	/**
	 * <bean id="userServiceImpl" class="com.wrf.service.impl.UserServiceImpl">
	 *	<property name="userDao" ref="userDaoImpl"></property>
	 * </bean>
	 */
	//自动赋值 与Resource标签作用相同
	@Autowired
	private UserDao userDao;
	
	/*
	public UserDao getUserDao() {
		return userDao;
	}
	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	*/
	
	@Override
	public void login() {
		userDao.login();
	}



}

package com.wrf.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.wrf.dao.UserDao;
import com.wrf.service.UserService;

//当前bean的id 默认类名小写首字母
@Component
public class UserServiceImpl implements UserService{
	/**
	 * <bean id="userServiceImpl" class="com.wrf.service.impl.UserServiceImpl">
	 *	<property name="userDao" ref="userDaoImpl"></property>
	 * </bean>
	 */
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}
	//依赖注入的bean类名
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void login() {
		userDao.login();
	}



}

package com.wrf.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.wrf.dao.UserDao;
//@Component
@Repository
public class UserDaoImpl implements UserDao {
	/**
	 * <bean id="userDaoImpl" class="com.wrf.dao.impl.UserDaoImpl">
	 * </bean>
	 * 
	 */
	@Override
	public void login() {
		System.out.println("====MyDao 欢迎登陆====");
	}

}

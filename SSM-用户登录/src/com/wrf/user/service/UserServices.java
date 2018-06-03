package com.wrf.user.service;
import com.wrf.user.model.User;

public interface UserServices {
	public User login(String account,String password) throws Exception;
}
	

package com.wrf.controller;

import com.wrf.service.UserService;

public class MyController {
		private UserService userService;
		
		public UserService getUserService() {
			return userService;
		}

		public void setUserService(UserService userService) {
			this.userService = userService;
		}
		

		public void test(){
			userService.login();
		}
}

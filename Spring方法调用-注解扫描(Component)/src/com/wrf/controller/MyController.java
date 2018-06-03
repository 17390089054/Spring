package com.wrf.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.wrf.service.UserService;
//@Component("myController")标注用于寻找bean的id值  默认:为bean类名首字母小写
@Component
public class MyController {
		/**
		 * <bean id="myController" class="com.wrf.controller.MyController">
		 *	<property name="userService" ref="userServiceImpl"></property>
		 * </bean>	
		 */
	
		private UserService userService;
		
		public UserService getUserService() {
			return userService;
		}
		
		//@Resource(name="userServiceImpl")用于标明依赖注入的bean的名称  默认:bean类名称首字母小写
		@Resource 
		public void setUserService(UserService userService) {
			this.userService = userService;
		}
		

		public void test(){
			userService.login();
		}
}

package com.wrf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.wrf.service.UserService;
//@Component("myController")标注用于寻找bean的id值  默认:为bean类名首字母小写
//@Component
//Controller作用与Component相同
@Controller
public class MyController {
		/**
		 * <bean id="myController" class="com.wrf.controller.MyController">
		 *	<property name="userService" ref="userServiceImpl"></property>
		 * </bean>	
		 */
	
		//org.springframework.beans.factory.annotation.Autowire
		//自动赋值 与Resource标签作用相同
		@Autowired
		private UserService userService;
		
		//@Resource(name="userServiceImpl")用于标明依赖注入的bean的名称  默认:bean类名称首字母小写
		//@Resource ==> javax.annotation.Resource
		//作用与Resource相同
		
		/*public void setUserService(UserService userService) {
			this.userService = userService;
		}
		*/

		public void test(){
			userService.login();
		}
}

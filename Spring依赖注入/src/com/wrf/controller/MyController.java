package com.wrf.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wrf.service.MyService;

public class MyController {
	private MyService myService;
	
	public MyService getMyService() {
		return myService;
	}
	public void setMyService(MyService myService) {
		this.myService = myService;
	}
	
	
	public void test02(){
		myService.test();
	}
	
	public static void main(String[] args) {
		MyController myController=new MyController();
		ApplicationContext ac=new ClassPathXmlApplicationContext("config/beans.xml");
		MyController mc = ac.getBean("myController",MyController.class);
		mc.test02();
	}
	
}

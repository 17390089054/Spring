package com.wrf.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wrf.controller.MyController;

public class Test02 {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("config/beans.xml");
		MyController mc=ac.getBean("myController",MyController.class);
		mc.test();
	}
}

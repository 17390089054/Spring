package com.wrf.test;

import org.junit.After;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
	protected static ApplicationContext applicationContext =null;
	public static void main(String[] args) {
		//实例化Student对象
		Student stu1=new Student();
		Student stu2=new Student();
		System.out.println(stu1);
		System.out.println(stu2);
		//Spring 管理的Student对象
		//读取配置文件
		applicationContext = new ClassPathXmlApplicationContext("config/beans.xml");
		//Student student1 =(Student) applicationContext.getBean("stu");
		//Student student2 =(Student) applicationContext.getBean("stu");
		//System.out.println(student2);
		
	}
	
	@Test
	public void test02(){
		applicationContext = new ClassPathXmlApplicationContext("config/beans.xml");
		Student stu=(Student)applicationContext.getBean("stu");
		System.out.println(stu);
	}
	
	@Test
	public void test03(){
		applicationContext = new ClassPathXmlApplicationContext("config/beans.xml");
		Student stu=(Student)applicationContext.getBean("stu2");
		System.out.println(stu);
	}
	
	@Test
	public void test04(){
		applicationContext = new ClassPathXmlApplicationContext("config/beans.xml");
		Student stu=(Student)applicationContext.getBean("stu3");
		System.out.println(stu);
	}
	
	@Test
	public void test05(){
		applicationContext = new ClassPathXmlApplicationContext("config/beans.xml");
		Student stu=(Student)applicationContext.getBean("stu1");
		System.out.println(stu);
	}
	
	@Test
	public void test(){
		Student stu=new Student();
		stu.setStuName("小明");
		stu.setStuAge(12);
		stu.setStuSex("男");
		stu.setStuGrade("大二");
		System.out.println(stu);
	}
	
}

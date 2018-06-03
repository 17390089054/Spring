package com.wrf.common.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.wrf.user.dao.UserDao;
import com.wrf.user.model.User;

public class MyBatisUtil {
	//资源路径
	private static final String PATH="mybatis-config.xml";
	//SqlSession工厂
	private static SqlSessionFactory factory;
	//私有化工具类 防止new创建对象
	private MyBatisUtil(){
		System.out.println("这是MybatisUtil的构造方法");
	};
	
	//初始化SqlSessionFactory
	public static void init(){
		try {
			InputStream input = Resources.getResourceAsStream(PATH);
			factory=new SqlSessionFactoryBuilder().build(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//获取SqlSession
	public static SqlSession getSqlSession(){
		init();
		return factory.openSession();
	}
	
	//关闭SqlSession
	public static void  closeSqlSession(SqlSession sqlSession){
		if(sqlSession!=null){
			sqlSession.close();
		}
	}
	
	//单例模式使用内部类维护单例的实例  主类被加载，内部类不会初始化 只能通过getInstance实例关键字
	public static class MyBatisUtilHolder{		
		private static MyBatisUtil instance=new MyBatisUtil();
	}
	
	public static MyBatisUtil getInstance(){
		return MyBatisUtilHolder.instance;
	}
	
}

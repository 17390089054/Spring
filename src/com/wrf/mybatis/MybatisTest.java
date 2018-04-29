package com.wrf.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisTest {
	public static void main(String[] args) {
		String Source="Mybatis-config.xml";
		SqlSession sqlSession=null;
		try {
			//读取配置文件
			InputStream is = Resources.getResourceAsStream(Source);
			//获取SQLSession对象
			SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(is);
			 sqlSession = sqlSessionFactory.openSession();
			//执行SQL
			//int num=sqlSession.insert("wrf.addUser");
			//int num = sqlSession.update("wrf.modifyUser");
			 //int num = sqlSession.delete("wrf.deleteUser");
			// List<Object> list = sqlSession.selectList("wrf.selectUser");
			 //System.out.println(num);
			//这是测试哈哈哈哈或或
			 //提交事务
			sqlSession.commit();
			
			System.out.println("成功!");
		
		} catch (IOException e) {
			System.out.println("失败!");
			e.printStackTrace();
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		
	}
}

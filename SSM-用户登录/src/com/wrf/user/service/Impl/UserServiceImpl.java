package com.wrf.user.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import com.wrf.common.util.MyBatisUtil;
import com.wrf.user.dao.UserDao;
import com.wrf.user.model.User;
import com.wrf.user.service.UserServices;

@Service
public class UserServiceImpl implements UserServices {

	SqlSession sqlSession=null;
	
	/**
	 * 用户登录主方法
	 * @param account
	 * @param password
	 * @return User
	 * @throws Exception
	 */
	public User login(String account,String password) throws Exception{
		User user=new User();
		try{
			sqlSession=MyBatisUtil.getSqlSession();
			UserDao userDao = sqlSession.getMapper(UserDao.class);
			user= userDao.queryUser(account, password);
			if(user==null){
				throw new Exception("账号或密码错误!");
			}
		}finally{
			MyBatisUtil.closeSqlSession(sqlSession);
		}
		
		return user;
	}

}

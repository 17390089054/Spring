package com.miaosha.project;

import com.miaosha.project.dao.UserDOMapper;
import com.miaosha.project.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"com.miaosha.project"}) //为当前Bean自动加载配置
@RestController
@MapperScan("com.miaosha.project.dao")
public class App 
{
    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping("/")
    public String print(){
        UserDO userDO=userDOMapper.selectByPrimaryKey(1);
        if(userDO==null){
            return "用户对象不存在";
        }else{
            return userDO.getName();
        }

    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //启动SpringBoot应用程序
        SpringApplication.run(App.class,args);
    }
}

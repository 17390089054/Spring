/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserService
 * Author:   knight
 * Date:     2019/2/15 0015 13:28
 * Description: UserServices接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.miaosha.project.service;
/**
 * @program: miaosha
 * @Date: 2019/2/15 0015 13:28
 * @Author: Mr.Deng
 * @Description:
 */

import com.miaosha.project.error.BusinessException;
import com.miaosha.project.service.model.UserModel;

/**
 * 〈一句话功能简述〉<br> 
 * 〈UserServices接口〉
 *
 * @author knight
 * @create 2019/2/15 0015
 * @since 1.0.0
 */

public interface UserService {
    //通过用户ID获取用户对象的方法
     UserModel getUserById(Integer Id);
     void register(UserModel userModel) throws BusinessException;

    /**
     *
     * @param telephone 用户注册的手机号
     * @param encryptedPassword 用户加密后的密码
     * @return UserModel
     * @throws BusinessException
     */
    UserModel validateLogin(String telephone,String encryptedPassword) throws BusinessException;
}

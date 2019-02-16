/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserServiceImpl
 * Author:   knight
 * Date:     2019/2/15 0015 13:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.miaosha.project.service.serviceImpl;

import com.miaosha.project.dao.UserDOMapper;
import com.miaosha.project.dao.UserPasswordDOMapper;
import com.miaosha.project.dataobject.UserDO;
import com.miaosha.project.dataobject.UserPasswordDO;
import com.miaosha.project.error.BusinessException;
import com.miaosha.project.error.EmBusinesError;
import com.miaosha.project.service.UserService;
import com.miaosha.project.service.model.UserModel;
import com.miaosha.project.validator.ValidationResult;
import com.miaosha.project.validator.ValidatorImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author knight
 * @create 2019/2/15 0015
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;
    @Autowired
    private ValidatorImpl validator;
    @Override
    public UserModel getUserById(Integer Id) {
        //调用UserDOMapper的方法获取对应的dataObject
        UserDO userDO=userDOMapper.selectByPrimaryKey(Id);
        if(userDO==null) return null;
        //通过UserId获取相应的加密密码信息
        UserPasswordDO userPasswordDO=userPasswordDOMapper.selectByUserId(userDO.getId());
        return convertFromUserDO(userDO,userPasswordDO);
    }


    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        if(userModel==null){
            throw new BusinessException(EmBusinesError.PARAMETER_VALIDACTION_ERROR);
        }
        /*if(StringUtils.isEmpty(userModel.getName())
                ||userModel.getGender()==null
                ||userModel.getAge()==null
                ||StringUtils.isEmpty(userModel.getTelephone())){
            throw new BusinessException(EmBusinesError.PARAMETER_VALIDACTION_ERROR);

        }*/

        ValidationResult result=validator.validate(userModel);
        if(result.isHasErrors()){
            throw new BusinessException(EmBusinesError.PARAMETER_VALIDACTION_ERROR,result.getErrMsg());
        }

        //实现model->dataobject方法
        UserDO userDO=convertFromUserModel(userModel);
        try{
            userDOMapper.insertSelective(userDO);
        }catch (DuplicateKeyException ex){
            throw new BusinessException(EmBusinesError.PARAMETER_VALIDACTION_ERROR,"手机号已重复");
        }

        userModel.setId(userDO.getId());
        UserPasswordDO userPasswordDO=convertPasswordFromUserModel(userModel);
        userPasswordDOMapper.insertSelective(userPasswordDO);
     return;
    }

    @Override
    public UserModel validateLogin(String telephone, String encryptedPassword) throws BusinessException {
        //通过用户手机号获取用户信息
        UserDO userDO=userDOMapper.selectByTelephone(telephone);
        if(userDO==null){
            throw new BusinessException(EmBusinesError.USER_LOGIN_FAIL);
        }
        UserPasswordDO userPasswordDO=userPasswordDOMapper.selectByUserId(userDO.getId());
        UserModel userModel=convertFromUserDO(userDO,userPasswordDO);

        //对比用户信息内加密的密码是否与传输进来的密码相匹配
        if(!StringUtils.equals(encryptedPassword,userModel.getEncryptPassword())){
            throw new BusinessException(EmBusinesError.USER_LOGIN_FAIL);
        }
        return userModel;

    }

    private UserPasswordDO convertPasswordFromUserModel(UserModel userModel) {
        if(userModel==null) return null;
        UserPasswordDO userPasswordDO=new UserPasswordDO();
        userPasswordDO.setEncryptPassword(userModel.getEncryptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }

    private UserDO convertFromUserModel(UserModel userModel){
        if(userModel==null) return null;
        UserDO userDO=new UserDO();
        BeanUtils.copyProperties(userModel,userDO);
        return userDO;
    }

    private UserModel convertFromUserDO(UserDO userDO, UserPasswordDO userPasswordDO){
        if(userDO==null) return null;
        UserModel userModel=new UserModel();
        BeanUtils.copyProperties(userDO,userModel);
        if(userPasswordDO!=null){
            userModel.setEncryptPassword(userPasswordDO.getEncryptPassword());
        }
        return userModel;
    }

}
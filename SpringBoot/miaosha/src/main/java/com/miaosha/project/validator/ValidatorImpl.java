/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ValidatorImpl
 * Author:   knight
 * Date:     2019/2/15 0015 23:15
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.miaosha.project.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author knight
 * @create 2019/2/15 0015
 * @since 1.0.0
 */
@Component
public class ValidatorImpl implements InitializingBean {
    private Validator validator;

    //实现校验方法并返回校验结果
    public ValidationResult validate(Object bean){
        ValidationResult result=new ValidationResult();
        Set<ConstraintViolation<Object>>constraintViolationSet= validator.validate(bean);
        if(constraintViolationSet.size()>0){
            //有错误
            result.setHasErrors(true);
            constraintViolationSet.forEach(constraintViolation->{
                String errMsg=constraintViolation.getMessage();
                String propertyName=constraintViolation.getPropertyPath().toString();
                result.getErrorMsgMap().put(propertyName,errMsg);
            });
        }
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //将Hibernate validator通过工厂的初始化方式使其实例化
        this.validator= Validation.buildDefaultValidatorFactory().getValidator();
    }
}
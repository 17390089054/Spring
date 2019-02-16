/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BusinessException
 * Author:   knight
 * Date:     2019/2/15 0015 15:28
 * Description: 通用异常
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.miaosha.project.error;

/**
 * 〈一句话功能简述〉<br> 
 * 〈通用异常〉
 *
 * @author knight
 * @create 2019/2/15 0015
 * @since 1.0.0
 */
//包装器业务异常类实现
public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;
    //直接接收EmBusinessError的传参用于构造业务异常
    public BusinessException(CommonError commonError){
        super();
        this.commonError=commonError;
    }

    //接收自定义errMsg的方式构造业务异常
    public BusinessException(CommonError commonError,String errMsg){
        super();
        this.commonError=commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BaseController
 * Author:   knight
 * Date:     2019/2/15 0015 16:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.miaosha.project.controller;

import com.miaosha.project.error.BusinessException;
import com.miaosha.project.error.EmBusinesError;
import com.miaosha.project.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author knight
 * @create 2019/2/15 0015
 * @since 1.0.0
 */
public class BaseController {

    public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";

    //定义exceptionhandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex){
        Map<String,Object> responseData=new HashMap<>();
        if(ex instanceof BusinessException){
            BusinessException businessException=(BusinessException)ex;
            CommonReturnType commonReturnType=new CommonReturnType();
            commonReturnType.setStatus("fail");
            responseData.put("errCode",businessException.getErrCode());
            responseData.put("errMsg",businessException.getErrMsg());
        }else{
            responseData.put("errCode", EmBusinesError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg",EmBusinesError.UNKNOWN_ERROR.getErrMsg());
        }

        return CommonReturnType.create(responseData,"fail");
    }
}
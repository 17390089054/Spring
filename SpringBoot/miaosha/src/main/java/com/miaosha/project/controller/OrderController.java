/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderController
 * Author:   knight
 * Date:     2019/2/16 0016 22:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.miaosha.project.controller;

import com.miaosha.project.error.BusinessException;
import com.miaosha.project.error.EmBusinesError;
import com.miaosha.project.response.CommonReturnType;
import com.miaosha.project.service.OrderService;
import com.miaosha.project.service.model.OrderModel;
import com.miaosha.project.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author knight
 * @create 2019/2/16 0016
 * @since 1.0.0
 */
@Controller("order")
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true",origins = "*")
public class OrderController extends  BaseController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value="/createOrder",method={RequestMethod.POST},consumes={CONTENT_TYPE_FORMED})
    @ResponseBody
    //封装下单请求
    public CommonReturnType createOrder(@RequestParam(name="itemId")Integer itemId,
                                        @RequestParam(name="amount")Integer amount,
                                        @RequestParam(name="promoId",required = false)Integer promoId) throws BusinessException {
        Boolean isLogin=(Boolean)httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if(isLogin==null||!isLogin.booleanValue()){
            throw new BusinessException(EmBusinesError.USER_NOT_LOGIN,"用户还未登录，无法下单");
        }

        //获取用户登录信息
        UserModel userModel=(UserModel)httpServletRequest.getSession().getAttribute("LOGIN_USER");


        OrderModel orderModel=orderService.createOrder(userModel.getId(),itemId,promoId,amount);


        return CommonReturnType.create(null);
    }





}
/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: OrderService
 * Author:   knight
 * Date:     2019/2/16 0016 20:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.miaosha.project.service;/**
 * @program: miaosha
 * @Date: 2019/2/16 0016 20:51
 * @Author: Mr.Deng
 * @Description:
 */

import com.miaosha.project.error.BusinessException;
import com.miaosha.project.service.model.OrderModel;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author knight
 * @create 2019/2/16 0016
 * @since 1.0.0
 */

public interface OrderService {
    //使用 1 通过前端url上传过来秒杀活动id ，然后下单接口内校验对应id是否属于对应商品且活动已开始
    //2 直接子啊下单接口内判断对应商品是否存在秒杀活动 若存在进行中的则以秒哈价格下单
    OrderModel createOrder(Integer userId,Integer itemId,Integer promoId,Integer amount) throws BusinessException;

}

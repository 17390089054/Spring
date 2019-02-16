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
    OrderModel createOrder(Integer userId,Integer itemId,Integer amount) throws BusinessException;

}

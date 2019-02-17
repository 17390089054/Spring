/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: PromoService
 * Author:   knight
 * Date:     2019/2/17 0017 9:32
 * Description: 秒杀活动接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.miaosha.project.service;/**
 * @program: miaosha
 * @Date: 2019/2/17 0017 9:32
 * @Author: Mr.Deng
 * @Description:
 */

import com.miaosha.project.service.model.PromoModel;

/**
 * 〈一句话功能简述〉<br> 
 * 〈秒杀活动接口〉
 *
 * @author knight
 * @create 2019/2/17 0017
 * @since 1.0.0
 */

public interface PromoService {
    //根据itemid获取即将进行的或正在进行的秒杀活动
    PromoModel getPromoByItemId(Integer itemId);

}

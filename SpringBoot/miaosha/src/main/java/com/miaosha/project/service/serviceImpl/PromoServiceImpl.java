/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: PromoServiceImpl
 * Author:   knight
 * Date:     2019/2/17 0017 9:35
 * Description: promoService实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.miaosha.project.service.serviceImpl;

import com.miaosha.project.dao.PromoDOMapper;
import com.miaosha.project.dataobject.PromoDO;
import com.miaosha.project.service.PromoService;
import com.miaosha.project.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉<br> 
 * 〈promoService实现类〉
 *
 * @author knight
 * @create 2019/2/17 0017
 * @since 1.0.0
 */
@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoModel getPromoByItemId(Integer itemId) {
        //获取对应商品的秒杀活动信息
        PromoDO promoDO=promoDOMapper.selectByItemId(itemId);

        //dataobject->model
        PromoModel promoModel=convertFromDataObject(promoDO);

        if(promoModel==null) return null;

        //判断当前时间是否秒杀活动即将开始或正在进行
        DateTime now=new DateTime();
        if(promoModel.getStartDate().isAfterNow()){
            promoModel.setStatus(1);
        }else if(promoModel.getEndDate().isBeforeNow()){
            promoModel.setStatus(3);
        }else{
            promoModel.setStatus(2);
        }

        return promoModel;
    }
    private PromoModel convertFromDataObject(PromoDO promoDO){
        if(promoDO==null){
            return null;
        }
        PromoModel promoModel=new PromoModel();
        BeanUtils.copyProperties(promoDO,promoModel);
        promoModel.setPromoItemPrice(new BigDecimal(promoDO.getPromoItemPrice()));
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));
        return promoModel;
    }





}
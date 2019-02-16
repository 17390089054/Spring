/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ItemService
 * Author:   knight
 * Date:     2019/2/16 0016 16:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.miaosha.project.service;/**
 * @program: miaosha
 * @Date: 2019/2/16 0016 16:05
 * @Author: Mr.Deng
 * @Description:
 */

import com.miaosha.project.error.BusinessException;
import com.miaosha.project.service.model.ItemModel;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author knight
 * @create 2019/2/16 0016
 * @since 1.0.0
 */

public interface ItemService {
    //创建商品
    ItemModel  createItem(ItemModel itemModel) throws BusinessException;
    //商品列表浏览
    List<ItemModel> listItem();
    //商品详情浏览
    ItemModel getItemById(Integer id);

}

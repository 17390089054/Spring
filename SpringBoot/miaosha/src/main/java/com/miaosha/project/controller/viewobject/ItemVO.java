/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ItemVO
 * Author:   knight
 * Date:     2019/2/16 0016 16:43
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.miaosha.project.controller.viewobject;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author knight
 * @create 2019/2/16 0016
 * @since 1.0.0
 */
public class ItemVO {
    private Integer id;

    //商品名称
    private String title;

    //商品价格
    private BigDecimal price;

    //商品的库存
    private Integer stock;

    //商品的描述
    private String description;

    //商品的销量
    private Integer sales;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    //商品描述图片的url
    private String imgUrl;

}
/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserVO
 * Author:   knight
 * Date:     2019/2/15 0015 14:20
 * Description: UI前端使用的模型对象
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.miaosha.project.controller.viewobject;

/**
 * 〈一句话功能简述〉<br> 
 * 〈UI前端使用的模型对象〉
 *
 * @author knight
 * @create 2019/2/15 0015
 * @since 1.0.0
 */
public class UserVO {
    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
    private String telephone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


}
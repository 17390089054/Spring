/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CommonReturnType
 * Author:   knight
 * Date:     2019/2/15 0015 14:54
 * Description: 通用返回类型
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.miaosha.project.response;

/**
 * 〈一句话功能简述〉<br> 
 * 〈通用返回类型〉
 *
 * @author knight
 * @create 2019/2/15 0015
 * @since 1.0.0
 */
public class CommonReturnType {
    //表明对应请求的返回处理结果 "success" or "fail"
    private String status;
    //若status==success 则data内返回前端需要的json数据
    //若status==fail  则data内使用通用的错误码格式
    private Object data;
    //普通创建方法
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }
    public static CommonReturnType create(Object result,String status){
       CommonReturnType type=new CommonReturnType();
       type.setStatus(status);
       type.setData(result);
       return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
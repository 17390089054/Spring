/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ValidationResult
 * Author:   knight
 * Date:     2019/2/15 0015 23:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.miaosha.project.validator;

import org.apache.commons.lang3.StringUtils;

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
public class ValidationResult {
    //校验结果是否有错
    private boolean hasErrors=false;

    //存放错误信息的map
    private Map<String,String>errorMsgMap=new HashMap<>();



    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    //实现通用的通过格式化字符串信息获取结果的msg方法
    public String getErrMsg(){
        return StringUtils.join(errorMsgMap.values().toArray(),",");
    }

}
/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CommonError
 * Author:   knight
 * Date:     2019/2/15 0015 15:17
 * Description: 通用错误码接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.miaosha.project.error;/**
 * @program: miaosha
 * @Date: 2019/2/15 0015 15:17
 * @Author: Mr.Deng
 * @Description:
 */

/**
 * 〈一句话功能简述〉<br> 
 * 〈通用错误码接口〉
 *
 * @author knight
 * @create 2019/2/15 0015
 * @since 1.0.0
 */

public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);
}

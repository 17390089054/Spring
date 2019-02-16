package com.miaosha.project.error;

/**
 * @program: miaosha
 * @Date: 2019/2/15 0015 15:20
 * @Author: Mr.Deng
 * @Description:
 */
public enum EmBusinesError implements  CommonError {

    //通用错误类型10001
    PARAMETER_VALIDACTION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),

    //20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001,"用户信息不存在"),
    USER_LOGIN_FAIL(20002,"用户手机号或密码不正确");

    private int errCode;
    private String errMsg;

    private EmBusinesError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg=errMsg;
        return this;
    }
}

package com.tdpark.sky.shield.enums;

public enum ErrorEnum {

    NO_AUTH("10001","请登陆后访问!"),
    EXPIRATION_TOKEN("10002","登录已失效,请重新登录!"),
    ILL_REQUEST("10003","非法请求!"),
    COM_PW_ERROR("20001","确认密码有误!"),
    USER_NOT_EXIST("20002","用户名不存在!"),
    PW_ERROR("20003","密码错误!"),
    USER_EXIST("20004","用户名已经存在!"),
    RANMOD_CODE_EXPIRATION("20005","验证码已过期,请重新刷新!"),
    RANMOD_CODE_ERROR("20006","验证码错误!"),
    
    FIELD_BE_NULL("80001","%s不能为空!"),
    LENGTH_MIN_ILL("80002","%s长度不能小于%s位!"),
    LENGTH_MAX_ILL("80003","%s长度不能大于%s位!"),
    VALUE_MIN_ILL("80004","%s不能小于%s!"),
    VALUE_MAX_ILL("80005","%s不能大于%s!"),
    SYS_EXCEPTION("99999","系统异常!"),
    ;
    private String code;
    private String desc;
    
    ErrorEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
    
}

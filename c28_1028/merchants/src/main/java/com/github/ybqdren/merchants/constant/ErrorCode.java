package com.github.ybqdren.merchants.constant;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/13 12:38
 * @package com.github.ybqdren.merchants.constant
 * @description
 * <h2> 错误码枚举定义 </h2>
 **/
public enum ErrorCode {
    SUCCESS(0,""),
    DUPLICATE_NAME(1,"商户名称重复"),
    EMPTY_LOGO(2,"商户 logo 为空"),
    EMPTY_BUSINESS_LICENSE(3,"商户营业执照为空"),
    ERROR_PHONE(4,"商户联系电话为空"),
    MERCHANTS_NOT_EXIST(5,"商户不存在"),
    EMPTY_NAME(6,"商户名为为空"),
    EMPTY_ADDRESS(7,"商户地址为空");

    /** 错误码 **/
    private Integer code;

    /** 错误描述 **/
    private String desc;

    ErrorCode(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

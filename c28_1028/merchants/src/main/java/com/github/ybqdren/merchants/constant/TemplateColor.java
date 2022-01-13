package com.github.ybqdren.merchants.constant;

import lombok.AllArgsConstructor;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/13 12:35
 * @package com.github.ybqdren.merchants.constant
 * @description 优惠券的背景色
 **/


public enum TemplateColor {
    RED(1,"红色"),
    GREEN(2,"绿色"),
    BLUE(3,"蓝色");

    /** 颜色代码 **/
    private Integer code;

    /** 颜色描述 **/
    private String color;

    TemplateColor(Integer code, String color) {
        this.code = code;
        this.color = color;
    }

    public Integer getCode() {
        return code;
    }

    public String getColor() {
        return color;
    }
}

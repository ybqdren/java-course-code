package com.github.ybqdren.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 用户领取优惠卷的请求对象 </h1>
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GainPassTemplateRequest {
    /** 用户 id */
    private Long userId;

    /** PassTemplate 对象 */
    private PassTemplate passTemplate;
}

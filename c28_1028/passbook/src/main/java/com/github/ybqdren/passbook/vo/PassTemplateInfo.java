package com.github.ybqdren.passbook.vo;

import com.github.ybqdren.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 优惠卷模板信息 </h1>
 **/


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplateInfo extends PassTemplate{
    /** 优惠卷模板 */
    private PassTemplate passTemplate;

    /** 优惠卷对应的商户 */
    private Merchants merchants;
}

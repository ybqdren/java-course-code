package com.github.ybqdren.passbook.vo;

import com.github.ybqdren.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 用户领取的优惠卷信息 </h1>
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassInfo {
    /** 优惠卷 */
    private Pass pass;

    /** 优惠卷模板 */
    private PassTemplate passTemplate;

    /** 优惠卷对应的商户 */
    private Merchants merchants;
}

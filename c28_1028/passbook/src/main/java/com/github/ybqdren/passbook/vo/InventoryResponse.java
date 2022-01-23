package com.github.ybqdren.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 库存请求响应 （没有过期的优惠卷）</h1>
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {
    /** 用户 ID */
    private Long userId;

    /** 优惠卷模板信息 */
    private List<PassTemplateInfo> passTemplateInfos;
}

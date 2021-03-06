package com.github.ybqdren.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zhao wen
 * @since 1.0.0
 *
 * <h1> 用户领取的优惠卷 </h1>
 **/


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pass {
    /** 用户 id */
    private Long userId;

    /** pass 在 HBase 中的 RowKey */
    private String rowKey;

    /** PassTemplate 在 HBase 中的 RowKey */
    private String templateId;

    /** 优惠卷 token ，有可能是 null，则填充 -1 */
    private String token;

    /** 领取日期 */
    private Date assignedDate;

    /** 消费日期，不为空代表已经被消费了 */
    private Date conDate;
}

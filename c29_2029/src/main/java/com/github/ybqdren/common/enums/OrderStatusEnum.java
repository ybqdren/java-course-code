package com.github.ybqdren.common.enums;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/24 17:06
 * @package com.github.ybqdren.common.enums
 * @description
 **/
public enum OrderStatusEnum {
        /**
         * 待支付
         */
        PENDING_PAY,
        /**
         * 支付中
         */
        PAYING,
        /**
         * 已支付
         */
        PAYED,
        /**
         * 退款中
         */
        REFUNDING,
        /**
         * 已退款
         */
        REFUNDED,
}

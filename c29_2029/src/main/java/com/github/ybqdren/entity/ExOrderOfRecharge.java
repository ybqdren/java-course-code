/*
 * Copyright (c) 2022, 重庆半山智能科技有限公司所有.
 */

package com.github.ybqdren.entity;

import com.github.ybqdren.common.enums.OrderStatusEnum;
import com.github.ybqdren.listener.OrderStatusAuditListener;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 会员充值订单
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners({AuditingEntityListener.class , OrderStatusAuditListener.class})
@Table(name = "ex_order_of_recharge")
public class ExOrderOfRecharge {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单状态
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatusEnum orderStatus;

    /**
     * 会员
     */
    @NotNull
    @ManyToOne
    @Column(name = "customer")
    private ExMember customer;

    /* 订单支付相关 */

    /**
     * 下单日期时间
     */
    @NotNull
    @Column(name = "ordering_datetime")
    private LocalDateTime orderingDateTime;

    /**
     * 实付金额
     */
    @NotNull
    @Column(name = "real_amount")
    private Integer realAmount;

    /**
     * 支付日期时间
     */
    @Column(name = "paying_date_time")
    private LocalDateTime payingDateTime;

    /**
     * 已完成支付日期时间
     */
    @Column(name = "payed_date_time")
    private LocalDateTime payedDateTime;

    /**
     * 退款日期时间
     */
    @Column(name = "refunding_date_time")
    private LocalDateTime refundingDateTime;

    /**
     * 已完成退款日期时间
     */
    @Column(name = "refunded_date_time")
    private LocalDateTime refundedDateTime;

    /* 充值相关 */

    /**
     * 充值金额
     */
    @NotNull
    @Column(name = "rechanrge_amount")
    private Integer rechargeAmount;
}

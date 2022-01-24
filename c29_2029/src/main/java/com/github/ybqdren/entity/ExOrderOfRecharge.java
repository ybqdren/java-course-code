/*
 * Copyright (c) 2022, 重庆半山智能科技有限公司所有.
 */

package com.github.ybqdren.entity;

import com.github.ybqdren.common.enums.OrderStatusEnum;
import com.github.ybqdren.listener.OrderStatusAuditListener;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;




/**
 * 会员充值订单
 * 实体字段中包含对象的解决办法：
 *      关联的对象不是实体
 *       - https://blog.csdn.net/Ditto_zhou/article/details/80829087 <JPA实体中字段映射补充和嵌入对象>
 *
 *      关联的对象是实体
 *
 * 多对一关系  ManyToOne 注解：
 *  - https://www.hxstrive.com/subject/open_jpa.htm?id=565 <@ManyToOne 注解>
 *  - https://blog.csdn.net/dbc_121/article/details/104997083 <讲明白Spring Data JPA实体关联注解>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners({AuditingEntityListener.class , OrderStatusAuditListener.class})
@Table(name = "ex_order_of_recharge")
public class ExOrderOfRecharge {

    public ExOrderOfRecharge(ExMember customer, Integer rechargeAmount) {
        this.customer = customer;
        this.rechargeAmount = rechargeAmount;
    }

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
    @Embedded
        @AttributeOverride(name = "name", column = @Column(name = "name"))
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

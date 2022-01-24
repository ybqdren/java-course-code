package com.github.ybqdren.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/24 15:09
 * @package com.github.ybqdren.entity
 * @description  Auditing 及其事件详解
 *
 * Spring Data JPA 为我们提供了审计功能的架构实现：
 *  提供了四个注解专门解决这个事情：
 *      - CreateBy 哪个用户创建的
 *      - CreatedDate 创建的时间
 *      - LastModifiedBy 修改实体的用户
 *      - LastModifiedDate 最后一次修改时间
 *
 *
 * 开启审计的步骤：
 *  1. 在实体中添加注解 {@link UserCustomerEntity}
 *  2. 实现审计类，让 JPA 知道当前的用户是谁 {@link com.github.ybqdren.repository.MyAuditoAware}
 *  3. 在启动类中开启 JPA 的审计功能 {@link com.github.ybqdren.SpringDataJpaApplication} 声明 bean 使用 @EnableJpaAuditing 开启
 **/


@Entity
@Table(name = "user_customer" ,schema = "test" , catalog = "")
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCustomerEntity {
    @Id
    @Column(name = "id" , nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreatedDate
    @Column(name = "create_time" , nullable = true)
    private Date createTime;

    @CreatedBy
    @Column(name = "create_user_id" , nullable = true)
    private Integer createUserId;

    @LastModifiedBy
    @Column(name = "last_modified_user_id" , nullable = true)
    private Integer lastModifiedUserId;

    @LastModifiedDate
    @Column(name = "last_modified_time" , nullable = true)
    private Date lastModifiedTime;

    @Column(name = "customer_name" , nullable = true , length = 50)
    private String customerName;

    @Column(name = "cuctomer_email" , nullable = true , length = 50)
    private String customerEmail;
}

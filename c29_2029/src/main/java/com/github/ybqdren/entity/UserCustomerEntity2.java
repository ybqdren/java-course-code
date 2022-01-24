package com.github.ybqdren.entity;

import com.github.ybqdren.repository.MyAuditoAware;
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
 * 继承自 {@link AbstractAuditable} 将审计的相关注解都抽象到次抽象类中，然后
 * 让实体去继承它
 **/


@Entity
@Table(name = "user_customer" ,schema = "test" , catalog = "")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCustomerEntity2 extends AbstractAuditable{
    @Id
    @Column(name = "id" , nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "create_time" , nullable = true)
    private Date createTime;

    @Column(name = "create_user_id" , nullable = true)
    private Integer createUserId;

    @Column(name = "last_modified_user_id" , nullable = true)
    private Integer lastModifiedUserId;

    @Column(name = "last_modified_time" , nullable = true)
    private Date lastModifiedTime;

    @Column(name = "customer_name" , nullable = true , length = 50)
    private String customerName;

    @Column(name = "cuctomer_email" , nullable = true , length = 50)
    private String customerEmail;
}

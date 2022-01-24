package com.github.ybqdren.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/24 15:39
 * @package com.github.ybqdren.auding
 * @description
 **/

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditable {
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

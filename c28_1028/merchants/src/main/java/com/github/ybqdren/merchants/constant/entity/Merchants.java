package com.github.ybqdren.merchants.constant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/13 13:01
 * @package com.github.ybqdren.merchants.constant.entity
 * @description
 *
 * <h1> 商户对象模型</h1>
 **/


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "merchants")
public class Merchants {

    /**  商户 id 主键 **/
    @Id
    @GeneratedValue     // 声明自动生成值，在数据库中也定义为了 auto_increment
    @Column(name = "id" , nullable = false)
    private Integer id;

    /** 商户名称，需要是全局唯一的  **/
    @Basic              // 声明表的基本列，表明在生成数据库时会在表中生成这一列，默认的都是 Basic，所以不定义也可以，与之对应的是 Transient
//    @Transient          // 表明此列不属于表，在自动生成时，不在表中生成这一列，如果声明为此且表中没有这一列，jpa 就会报错（即此列已经存在于数据表中）
    @Column(name = "name" , unique = true , nullable = false)
    private String name;

    /** 商户 logo **/
    @Basic
    @Column(name = "logo_url" , nullable = false)
    private String logoUrl;

    /** 商户营业执照 **/
    @Basic
    @Column(name = "business_license_url" , nullable = false)
    private String businessLicenseUrl;

    /** 商户联系电话 **/
    @Basic
    @Column(name = "phone" , nullable = false)
    private String phone;

    /** 商户地址 **/
    @Basic
    @Column(name = "address" , nullable = false)
    private String address;

    /** 商户是否通过审核 **/
    @Basic
    @Column(name = "is_audit" , nullable = false)
    private Boolean isAudit;
}

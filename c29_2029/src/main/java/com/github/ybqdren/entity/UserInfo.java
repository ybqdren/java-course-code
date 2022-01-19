package com.github.ybqdren.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/17 17:14
 * @package org.github.ybqdren.entity
 * @description
 *
 * 参考： https://segmentfault.com/a/1190000019014245
 * strategy有四个枚举值：
 *
 *     GenerationType.TABLE 使用一个额外的表来存储主键；
 *     GenerationType.SEQUENCE 使用序列的方式存储，且需要数据库底层支持；
 *     GenerationType.IDENTITY 由数据库生成，一般为主键自增等；
 *     GenerationType.AUTO 表示由程序生成，不声明则默认为该属性；
 **/

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "userInfo")
public class UserInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id" , unique = true , nullable = false)
    private Long id;

    @Column(name = "nika_name" , nullable = false , length = 24)
    private String nikaName;

    @Column(name = "first_name" , nullable = true)
    private String firstName;

    @Column(name = "last_name" , nullable = true)
    private String lastName;

    @Column(name = "email_address" , nullable = true)
    private String emailAddress;
}


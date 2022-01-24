/*
 * Copyright (c) 2022, 重庆半山智能科技有限公司所有.
 */

package com.github.ybqdren.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * 会员
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ExMember  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 会员名称
     */

    private String name;

    /**
     * 电话
     */
    private String phone;


    /**
     * 个人简介
     */
    private String intro;

}

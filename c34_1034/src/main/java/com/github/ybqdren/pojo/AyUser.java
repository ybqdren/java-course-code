package com.github.ybqdren.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * <h1> 用户实体 </h1>
 * @author zhao wen
 * @since 1.0.0
 **/

@Data
public class AyUser implements Serializable {
    private Integer id;
    private String name;
    private String password;
}

package com.github.ybqdren.service;

import com.github.ybqdren.pojo.AyUser;

import java.util.List;

/**
 * @author zhao wen
 * @since 1.0.0
 **/

public interface AyUserService {

    /**
     * <h2> 查询所有用户 </h2>
     * @return
     */
    List<AyUser> findAll();
}

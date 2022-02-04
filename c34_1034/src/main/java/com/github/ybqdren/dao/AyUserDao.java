package com.github.ybqdren.dao;

import com.github.ybqdren.pojo.AyUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <h1> {@link AyUser} </h1>
 * @author zhao wen
 * @since 1.0.0
 **/

@Repository
public interface AyUserDao {
    List<AyUser> findAll();
}

package com.github.ybqdren.repository;

import com.github.ybqdren.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 使用 SpEl 表达式 </h1>
 **/
public interface UserSpELRepository extends JpaRepository<User, Long> {
    /** 直接通过 entity 的 name 去查， jpa 会自动去找*/
    @Query("select u from #{#entityName} u where u.name = ?1")
    List<User> findByName(String name);
}

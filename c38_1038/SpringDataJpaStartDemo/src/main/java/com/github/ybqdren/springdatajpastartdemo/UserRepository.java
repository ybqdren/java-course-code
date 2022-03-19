package com.github.ybqdren.springdatajpastartdemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * dao
 *
 * @Author zhao wen
 * @Version 0.0.1
 * @Date 2022/3/19
 */

public interface UserRepository extends JpaRepository<User, Long> {
}

package com.github.ybqdren.repository;

import com.github.ybqdren.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author zhao wen
 * @since 1.0.0
 **/
public interface UserRepositoryNativeSQL extends JpaRepository<User,Integer> ,
                                                    JpaSpecificationExecutor<User> {
    /** 对原生 SQL 的分页支持示例，以 MySQL 为例 */
    @Query(value = "select * from user where name = ?1 /* #pageable# */ " ,
            countQuery = "select count(*) from user where name = ?1" ,
            nativeQuery = true)
    Page<User> findByName(String name , Pageable pageable);
}

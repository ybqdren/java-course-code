package com.github.ybqdren.repository;

import com.github.ybqdren.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/19 13:02
 * @package com.github.ybqdren.repository
 * @description
 *
 * 使用注解的方式操作数据库 （原始的 sql 语句）
 **/
public interface UserJpaNativeSQLRepository extends JpaRepository<User, Long> {

    /** 直接使用原始的 SQL */
    @Query(value = "SELECT * FROM USER WHERE EMAIL = ?1",nativeQuery = true)
    User findByEmail(String email);

    
}

package com.github.ybqdren.repository;

import com.github.ybqdren.entity.User;
import com.github.ybqdren.entity.UserInfo;
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
public interface UserInfoJpaNativeSQLRepository extends JpaRepository<UserInfo, Long> {

    /** 直接使用原始的 SQL */
    @Query(value = "SELECT * FROM user_info WHERE email_address = ?1",nativeQuery = true)
    UserInfo findByEmailAddress(String emailAddress);

    @Query(value = "SELECT * FROM user_info WHERE first_name = ?1 ORDER BY ?2",nativeQuery = true)
    List<UserInfo> findByFirstName(String firstName , String sort);

    
}

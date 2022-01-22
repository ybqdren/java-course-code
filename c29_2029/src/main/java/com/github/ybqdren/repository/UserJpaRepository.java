package com.github.ybqdren.repository;

import com.github.ybqdren.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/19 13:02
 * @package com.github.ybqdren.repository
 * @description
 *
 * 使用注解的方式操作数据库
 **/
public interface UserJpaRepository extends JpaRepository<User, Long> {

    // User(id=2, name=ybqdren, email=withzhaowen@126.com)
    @Query("select u from User u where u.email = ?1 ")
    User findByEmail(String emailAddress);

    @Query("select u from User u where u.name like %?1")
    List<User> findByNameEndingWith(String userName);

    /** 直接使用原始的 SQL */
/*    @Query(value = "SELECT * FROM USER WHERE EMAIL = ?1",nativeQuery = true)
    User findByEmail(String email);*/

    /** 注意： Native Query 不支持直接 Sort 的参数查询 */
    /** 错误写法 */
/*    @Query(value = "select * from user where name = ?1" , nativeQuery = true)
    List<User> findById(String name , Sort sort);*/
    /** 正确写法 */
    @Query(value = "select * from user where name = ?1 order by ?2" , nativeQuery = true)
    List<User> findById(String name , String sort);


}
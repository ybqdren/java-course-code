package com.github.ybqdren.repository;

import com.github.ybqdren.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /** Query 排序 */
    /*
        在排序实例中实际使用的属性需要与实体模型里面的字段相匹配，着意味着它们需要解析为查询中使用的属性或别名。
        这是一个 state_field_path_expression JPQL 定义，并且 Sort 的对象支持一些特定的函数。
     */
    @Query("select u from User u where u.name like ?1%")
    List<User> findByAndSort(String name , Sort sort);

    @Query("select u.id , LENGTH(u.name) as fn_len from User u where u.name like ?1%")
    List<Object[] > findByAsArrayAndSort(String name , Sort sort);

    /** Query 分页 */
    @Query(value = "select u from User u where u.name = ?1")
    Page<User> findByName(String name , Pageable pageable);
}

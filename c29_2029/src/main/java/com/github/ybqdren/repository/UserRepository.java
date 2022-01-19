package com.github.ybqdren.repository;

import com.github.ybqdren.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/18 12:25
 * @package org.github.ybqdren.Repository
 * @description  {@link CrudRepository} 使用示例
 **/


public interface UserRepository extends CrudRepository<User,Long> {
    /** select * from user t where t.name = ?1 and id = ?2 */
    public List<User> findByNameAndId(String userName, Long id);

    /** select *  from user  order by id desc; */
    public List<User> findByNameEndingWithOrderByIdDesc(String name);

    /** select *  from user t where t.name like ?1;  (email)*/
    public List<User> findByNameLike(String name);

    /** select *  from user t where t.email like ?1; (%email%)*/
    public List<User> findByEmailContaining(String email);
}

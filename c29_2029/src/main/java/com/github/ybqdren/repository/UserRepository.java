package com.github.ybqdren.repository;

import com.github.ybqdren.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.parser.PartTree;

import java.util.List;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/18 12:25
 * @package org.github.ybqdren.Repository
 * @description  {@link CrudRepository} 使用示例
 *
 * @see {@link org.springframework.data.repository.query.parser.PartTree}  将字符串解析为 tree 或者 {@link PartTree.OrPart}
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

    /** select count(*) from user t;  查询总数 */
    public long countByName(String userName);

    /** delete from user where name = ?1; 根据一个字段进行删除操作 */
    public long deleteByName(String userName);
}

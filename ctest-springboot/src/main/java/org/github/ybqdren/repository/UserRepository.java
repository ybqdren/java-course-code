package org.github.ybqdren.repository;

import org.github.ybqdren.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/18 12:25
 * @package org.github.ybqdren.Repository
 * @description  {@link CrudRepository} 使用示例
 **/


public interface UserRepository extends CrudRepository<User,Long> {

}

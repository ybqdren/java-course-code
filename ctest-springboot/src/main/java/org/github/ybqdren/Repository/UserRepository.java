package org.github.ybqdren.Repository;

import org.github.ybqdren.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/18 12:25
 * @package org.github.ybqdren.Repository
 * @description
 **/
public interface UserRepository extends CrudRepository<User,Long> {
}

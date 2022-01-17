package org.github.ybqdren.dao;

import org.github.ybqdren.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/17 17:14
 * @package org.github.ybqdren.dao
 * @description
 **/
public interface UserRepository extends JpaRepository<User,Long> {

//    @Query("select u from User u where u.firstname like %?1")
    User findByEmailAddress(String emailAddress);
}

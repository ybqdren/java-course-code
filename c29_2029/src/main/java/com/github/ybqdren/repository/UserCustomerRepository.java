package com.github.ybqdren.repository;

import com.github.ybqdren.entity.UserCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/24 15:31
 * @package com.github.ybqdren.repository
 * @description
 **/
public interface UserCustomerRepository extends JpaRepository<UserCustomerEntity, Long> {

}

package com.github.ybqdren.repository;

import com.github.ybqdren.entity.ExOrderOfRecharge;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/24 17:15
 * @package com.github.ybqdren.repository
 * @description
 **/
public interface OrderQueryJpaRepository extends JpaRepository<ExOrderOfRecharge , Long> {

}

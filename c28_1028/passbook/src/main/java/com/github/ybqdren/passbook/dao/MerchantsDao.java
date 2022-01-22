package com.github.ybqdren.passbook.dao;

import com.github.ybqdren.passbook.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> Merchants Dao 接口 </h1>
 *
 * <p> 商户部分通用的可以放在一个新的模块中，方便重用 </p>
 **/
public interface MerchantsDao extends JpaRepository<Merchants, Integer> {

    /**
     * <h2> 通过 id 获取商户对象 </h2>
     * @param id 商户 id
     * @return {@link Merchants}
     */
    Merchants findbyId(Integer id);

    /**
     * <h2> 根据商户名称获取商户对象 </h2>
     * @param name 商户名称
     * @return {@link Merchants}
     */
    Merchants findByName(String name);

    /**
     * <h2> 根据商户 ids 获取商户对象 </h2>
     * @param ids 商户 ids
     * @return {@link Merchants}
     */
    List<Merchants> findByIdIn( List<Integer> ids);
}

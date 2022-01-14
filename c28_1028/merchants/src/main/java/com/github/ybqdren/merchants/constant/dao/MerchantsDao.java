package com.github.ybqdren.merchants.constant.dao;

import com.github.ybqdren.merchants.constant.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/14 12:17
 * @package com.github.ybqdren.merchants.constant.dao
 * @description
 *
 * <h1> Merchants Dao 接口 </h1>
 * <p> 定义 orm 的方法 ，继承 jpa 提供的方法，jpa 会自动为我们进行实体映射表 </p>
 *
 * JpaRepository：用 java 对象映射 sql，继承 {@link  JpaRepository} 之后定义接口就可以自动生成 sql 语句
 **/
public interface MerchantsDao extends JpaRepository<Merchants, Integer> {
    /**
     * <h2> 根据 id 获取商户对象 </h2>
     * @param id 商户 id
     * @return {@link  Merchants}
     */
    Merchants findbyId(Integer id);

    /**
     * <h2> 根据商户名称获取商户对象 </h2>
     * @param name 商户名称（唯一）
     * @return {@link  Merchants}
     */
    Merchants findByName(String name);


}

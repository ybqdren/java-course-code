package com.github.ybqdren.merchants.service;

import com.github.ybqdren.merchants.vo.CreateMerchantsRequest;
import com.github.ybqdren.merchants.vo.PassTemplate;
import com.github.ybqdren.merchants.vo.Response;

/**
 * @author zhao wen
 * @since 1.0.0
 *
 * <h1>对商户服务接口定义</h1>
 **/
public interface IMerchantServ {
    /**
     * <h2> 创建商户服务 </h2>
     * @param request {@link  CreateMerchantsRequest} 创建商户请求
     * @return {@link Response}
     */
    Response createMechants(CreateMerchantsRequest request);

    /**
     * <h2> 根据 id 构造商户信息 </h2>
     * @param id 商户 id
     * @return {@link Response}
     */
    Response buildMerchantsInfoById(Integer id);

    /**
     * <h2> 投放优惠卷</h2>
     * @param template {@link  PassTemplate} 优惠卷对象
     * @return {@link  Response}
     */
    Response dropPassTemplate(PassTemplate template);
}

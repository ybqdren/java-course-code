package com.github.ybqdren.passbook.service;

import com.github.ybqdren.passbook.vo.Response;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 获取库存信息：只返回用户没有领取的。即优惠卷库存功能实现接口 </h1>
 **/
public interface IInventoryService {
    /**
     * <h2> 获取库存信息（方便用户在客户端上查看） </h2>
     * @param userId 用户 id
     * @return {@link Response}
     * @throws Exception
     */
    Response getInventoryInfo(Long userId) throws  Exception;


}

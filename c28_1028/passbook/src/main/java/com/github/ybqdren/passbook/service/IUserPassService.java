package com.github.ybqdren.passbook.service;

import com.github.ybqdren.passbook.vo.Pass;
import com.github.ybqdren.passbook.vo.Response;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 获取用户个人优惠卷信息 </h1>
 **/
public interface IUserPassService {
    /**
     * <h2> 获取用户个人优惠卷信息 及 我的优惠卷功能实现 </h2>
     * @param userId 用户 id
     * @return {@link Response}
     * @throws Exception
     */
    Response getUserPassInfo(Long userId) throws Exception;

    /**
     * <h2> 获取用户已经消费了的优惠卷，即已使用优惠卷功能实现 </h2>
     * @param userId 用户 id
     * @return {@link Response}
     * @throws Exception
     */
    Response getUserUsedPassInfo(Long userId) throws Exception;

    /**
     * <h2> 获取用户所有的优惠卷 （上面两个接口的功能之和）</h2>
     * @param userId 用户 id
     * @return {@link Response}
     * @throws Exception
     */
    Response getUserAllPassInfo(Long userId) throws Exception;

    /**
     * <h2> 用户使用优惠卷 </h2>
     * <p> 用户领取了优惠卷后会返回优惠卷的信息 </p>
     * @param pass {@link Pass}
     * @return {@link Response}
     * @throws Exception
     */
    Response getUserPass(Pass pass) throws Exception;

}

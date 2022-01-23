package com.github.ybqdren.passbook.service;

import com.github.ybqdren.passbook.vo.Response;
import com.github.ybqdren.passbook.vo.User;
import org.springframework.stereotype.Service;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 用户服务： 创建 User 服务 </h1>
 **/

@Service
public interface IUserService {
    /**
     * <h2> 创建用户 </h2>
     * @param user {@link User}
     * @return {@link Response}
     */
    Response createUser(User user) throws Exception;
}

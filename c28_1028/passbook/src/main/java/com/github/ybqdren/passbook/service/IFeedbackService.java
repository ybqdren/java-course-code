package com.github.ybqdren.passbook.service;

import com.github.ybqdren.passbook.vo.Feedback;
import com.github.ybqdren.passbook.vo.Response;

/**
 * @author zhao wen
 * @since 1.0.0
 *
 * <h1> 评论功能：即用户评论相关功能实现 </h1>
 **/

public interface IFeedbackService {
    /**
     * <h2> 创建评论 </h2>
     * @param feedback {@link Feedback}
     * @return {@link Response}
     */
    Response createFeedback(Feedback feedback);

    /**
     * <h2> 获取用户评论 </h2>
     * @param userId 用户 id
     * @return {@link Response}
     */
    Response getFeedback(Long userId);
}

package com.github.ybqdren.passbook.service.impl;

import com.github.ybqdren.passbook.vo.GainPassTemplateRequest;
import com.github.ybqdren.passbook.vo.Response;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 用户领取优惠卷功能实现 </h1>
 **/
public interface IGanPassTemplateService {

    /**
     * <h2> 用户领取优惠卷  </h2>
     * @param request {@link GainPassTemplateRequest}
     * @return {@link Response}
     * @throws Exception
     */
    Response gainPassTemplate(GainPassTemplateRequest request) throws  Exception;
}

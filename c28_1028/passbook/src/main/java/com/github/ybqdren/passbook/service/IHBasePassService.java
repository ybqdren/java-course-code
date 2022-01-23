package com.github.ybqdren.passbook.service;

import com.github.ybqdren.passbook.vo.PassTemplate;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> Pass HBase 服务 </h1>
 **/
public interface IHBasePassService {
    /**
     * <h2> 将 PassTemplate 写入 HBase </h2>
     * @param passTemplate {@link PassTemplate}
     * @return true / false
     */
    boolean dropPassTemplateToHBase(PassTemplate passTemplate);
}

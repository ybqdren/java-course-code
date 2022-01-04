package org.github.ybqdren.common.util;

import org.github.ybqdren.common.annotation.Required;
import org.github.ybqdren.common.enumeration.UserLevel;

import java.lang.annotation.Annotation;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/4 12:20
 * @package org.github.ybqdren.common.util
 * @description 通过注解来判断用户等级，默认是游客
 **/
public class AnnotationUtil {
    /**
     * 通过注解来判断用户等级，默认是游客
     * @param annotations
     * @return
     */
    public static UserLevel findRequired(Annotation[] annotations){
        for(Annotation annotation : annotations){
            Class<? extends  Annotation> aClass = annotation.annotationType();   // 获取注解的类型
            Required required = aClass.getAnnotation(Required.class);
            if(required != null){
                return required.level();   // 通过获取 Required 注解中设置的用户等级来返回用户等级
            }
        }
        return UserLevel.TOURIST;
    }
}

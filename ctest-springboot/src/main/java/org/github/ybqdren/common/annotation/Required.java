package org.github.ybqdren.common.annotation;

import org.github.ybqdren.common.enumeration.UserLevel;

import java.lang.annotation.*;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/4 12:24
 * @package org.github.ybqdren.common.annotation
 * @description 用户等级
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Required {
    UserLevel level() default UserLevel.TOURIST;
}

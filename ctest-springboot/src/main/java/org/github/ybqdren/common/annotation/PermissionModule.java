package org.github.ybqdren.common.annotation;

import java.lang.annotation.*;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/4 14:12
 * @package org.github.ybqdren.common.annotation
 * @description
 **/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionModule {
    String value() default "";
}

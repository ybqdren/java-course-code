package org.github.ybqdren.common.annotation;

import java.lang.annotation.*;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/30 16:55
 * @package org.github.ybqdren.common.annotation
 * @description  路由信息，记录路由权限，模块等信息
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionMeta {
    String value();

    String permission() default "";

    String module() default "";

    boolean mount() default true;
}

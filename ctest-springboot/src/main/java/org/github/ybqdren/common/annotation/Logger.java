package org.github.ybqdren.common.annotation;

import java.lang.annotation.*;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/30 16:43
 * @package org.github.ybqdren.common.annotation
 * @description
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logger {
    String log();
}

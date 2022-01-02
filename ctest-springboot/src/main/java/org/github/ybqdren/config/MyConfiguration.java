package org.github.ybqdren.config;

import org.github.ybqdren.common.interceptor.LogInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/1 9:57
 * @package org.github.ybqdren.config
 * @description
 **/


@Configuration(proxyBeanMethods = false)
public class MyConfiguration {
    @Bean
    public LogInterceptor logInterceptor() {
        return new LogInterceptor();
    }
}

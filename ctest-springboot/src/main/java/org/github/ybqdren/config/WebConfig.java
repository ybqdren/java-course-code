package org.github.ybqdren.config;

import org.github.ybqdren.common.interceptor.LogIntereptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/31 17:06
 * @package org.github.ybqdren.config
 * @description
 **/

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogIntereptor());
//        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

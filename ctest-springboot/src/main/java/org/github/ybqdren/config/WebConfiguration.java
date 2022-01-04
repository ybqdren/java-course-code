package org.github.ybqdren.config;

import org.github.ybqdren.common.bean.PermissionMetaCollector;
import org.github.ybqdren.common.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC 配置
 *
 * @author pedro@TaleLin
 * @author colorful@TaleLin
 */
@Configuration(proxyBeanMethods = false)
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private LogInterceptor logInterceptor;

    @Bean
    public PermissionMetaCollector addPermissionMetaCollector(){
        return new PermissionMetaCollector();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
    }


}

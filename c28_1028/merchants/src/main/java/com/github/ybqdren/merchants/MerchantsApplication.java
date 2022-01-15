package com.github.ybqdren.merchants;

import com.github.ybqdren.merchants.security.AuthCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@EnableJpaRepositories(basePackages = {"com.github.ybqdren.merchants"}) // 指定扫描 jpa 的路径
@EntityScan(basePackages = {"com.github.ybqdren.merchants.entity"})     // 指定扫描实体的路径
@SpringBootApplication
public class MerchantsApplication extends WebMvcConfigurerAdapter {

    @Resource
    private AuthCheckInterceptor authCheckInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(MerchantsApplication.class, args);
    }

    /** 拦截 /merchants/** 路径 ，将 AuthCheckInterceptor 拦截器注册到此路径中 **/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authCheckInterceptor)
                .addPathPatterns("/merchants/**");
        super.addInterceptors(registry);
    }
}

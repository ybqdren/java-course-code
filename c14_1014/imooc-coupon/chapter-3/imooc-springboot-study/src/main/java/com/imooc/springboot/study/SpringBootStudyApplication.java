package com.imooc.springboot.study;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <h1>应用启动类</h1>
 * 1. @EnableScheduling 允许当前的应用开启定时任务
 * 2. @EnableAsync 开启异步支持
 * Created by Qinyi.
 */
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class SpringBootStudyApplication {

    public static void main(String[] args) {

        // 1. 通过静态 run 方法
        SpringApplication.run(SpringBootStudyApplication.class, args);

        // 2. 通过 api 调整应用行为
        SpringApplication application =
                new SpringApplication(SpringBootStudyApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);

        // 3. SpringApplicationBuilder Fluent Api, 链式调用
        new SpringApplicationBuilder(SpringBootStudyApplication.class)
                .bannerMode(Banner.Mode.OFF)
//                .web(WebApplicationType.NONE)
                .run(args);
    }
}

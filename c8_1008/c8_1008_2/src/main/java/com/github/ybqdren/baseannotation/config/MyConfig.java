package com.github.ybqdren.baseannotation.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/7 12:34
 * @package com.github.ybqdren.baseannotation.config
 * @description
 **/

/**
 注解 {@link Configuration}：
    - 相当于 Spring 的 XML 配置文件
    - 从 Spring3.0 开始，可以使用 @Configuration 定义配置类，可替换 xml 配置文件
    配置类内部包含一个或多个被 @Bean 注解的方法，这些方法将会被 AnnotationConfigApplicationContext
        或 AnnotationConfigWebApplicationContext 类进行扫描
    并用于构建 bean 的定义对象，初始化 Spring 容器
 */
@Configuration
public class MyConfig {
}

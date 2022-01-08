package com.github.ybqdren.baseannotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

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

注解 {@link Import}：
    - 相当于 spring 配置文件中的 <import> 标签
    - 用来组合多个配置类，在引入其他配置类时，可以不用再写 @Configuration 注解。当然，写上也没有问题

    属性：
        - value： 用来指定其他配置类的字节码文件
 */
@Configuration
@ComponentScan(basePackages = {"com.github.ybqdren.baseannotation.service"})
@Import({MyProperties.class})
public class MyConfig {
    public MyConfig() {
        System.out.println("IOC 容器初始化");
    }
}

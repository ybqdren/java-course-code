package com.github.ybqdren.baseannotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/6 12:12
 * @package com.github.ybqdren.baseannotation
 * @description
 **/


/**
 注解 {@link org.springframework.context.annotation.ComponentScan}:
        - 从约定的扫描路径中，识别标注了组件注册注解的类（@Componet,@Controller,@Service,@Repository），并把这些类自动注册到 spring IOC 中
        也就是我们通常所言的 bean，IOC 容器是 Spring 的特色之一，可以使用它管理 bean，当然
        @ComponentScan 注解也被托管给了 IOC 容器
        - 注解再类上面，一般配合 @Configuration 注解一起使用

        属性：
            - basePackages：用于指定要扫描的包
            - value：和 basePackages 作用一样

 @ref
        https://www.cnblogs.com/east7/p/14390877.html - SpringBoot 注解之 @ComponentScan 用法和实现原理

 */
@SpringBootApplication
public class MySpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class);
    }
}

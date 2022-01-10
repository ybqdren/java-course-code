# Spring Boot 工程目录说明

![](img/1.SpringBoot工程目录结构说明.PNG)


Spring Boot 会使用默认的端口号（8080）启动服务，如果要切换端口，需要修改核心配置文件 application.properties 文件。

在分布式和微服务开发中，使用的大部分是 YAML 文件，而不是 properties 文件。



# 多文件配置

在 Spring Cloud 中，一个服务下可以包含多个实例，因此同一个工程可能需要在不同的配置（如端口）下启动。

为了更好地适应多个环境的运行， Spring Boot 配置项会按照一定的优先级进行加载，优先级从高到低的顺序如下：
- 命令行参数
- 来自 java:comp/env 的 JNDI 属性
- Java 系统属性（System.getProperties()）
- 操作系统环境变量
- RandomValuePropertySource 配置的 random.* 属性值
- jar 包外部的 application-{profile}.properties 或 application.yml（带 spring.profile ）配置文件
- jar 包内部的 application-{profile}.properties 或 application.yml(带 spring.profile )配置文件
- jar 包外部的 application-{profile}.properties 或 application.yml（不带 spring.profile ）配置文件
- jar 包内部的 application-{profile}.properties 或 application.yml(不带 spring.profile )配置文件
- @Configuration 注解类上的 @PropertySource
- 通过 SpringApplication.setDefaultProperties 指定的默认属性

上面的顺序比较复杂，在大部分情况下，并不需要使用所有的配置。
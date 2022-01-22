1. 创建数据库 test_jpa

2. 第一次运行 SpringDataJpaApplicaiton ，会自动映射创建数据表

> 测试方法在 src\test\java\com\github\ybqdren\SpringDataJpaTestApplication.java 中





# SpEL 表达式
自 Spring Data JPA 1.4 以后，可支持在 @Query 中使用 SpEL 表达式来接收变量

## SpEL 支持的变量
| 变量名        | 使用方式                           | 描述                                                                                                            |
|------------|--------------------------------|---------------------------------------------------------------------------------------------------------------|
| entityName | select x from #{#entityName} x | 根据指定的 Repository 自动插入相关的 entityName。<br/> 有两种方式能被解析出来：<br/> - 如果定义了 @Entity 注解，直接用其属性名<br/> - 如果没定义，直接用实体类的名称 |



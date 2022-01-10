# Spring Cloud 的各个组件的简介

为了构建微服务架构，Spring Cloud 容纳了很多分布式开发的组件：
- Sping Cloud Config：配置管理，允许被集中化放到远程服务器中。目前支持本地存储、Git 和 SVN 等。
- Spring Cloud Bus ：分布式事件、消息总线、用于集群（如配置发生变化）中传播事件状态，可以与 Spring Cloud Config 联合实现热部署。
- Netflix Eureka ：服务治理中心，它提供微服务的治理，包括微服务的注册和发现，是 Spring Cloud 的核心组件。
- Netflix Hystrix ：断路器，在某个组件因为某些原因无法响应或者响应超时之际进行熔断，以避免其他微服务调用该组件造成大量线程积压。它提供了更为强大的容错能力。
- Netflix Zuul：API 网关，它可以拦截 Spring Cloud 的请求，提供动态路由功能。它还可以限流，保护微服务持续可用，还可以通过过滤器提供验证安全。
- Spring Cloud Security：它是基于 Sqpring Security 的，可以给微服务提供安全控制。
- Spring Cloud Sleuth：它是一个日志收集工具包，可以提供分布式追踪的功能。它封装了 Dapper 和 log-based 追踪以及 Zipkin 和 HTrace 操作。
- Spring Cloud Stream：分布式数据流操作，它封装了关于 Redis 、RabbitMQ、Kafka 等数据流的开发工具。
- Netflix Ribbon：提供客户端的负载均衡。它提供了多种负载均衡方案，我们可以根据自己的需求选择某种方案，它还可以配合服务发现和断路器使用。
- Netflix Turbine：Turbine 是聚合服务器发送事件流数据的工具，用来监控集群下 Hystrix 的 mertics 情况。
- OpenFeign：它是一个声明式的调用方案，可以屏蔽 REST 风格的代码调用，而采用接口声明方式调用，这样就可以有效减少不必要的代码，进而提高代码的可读性。
- Spring Cloud Task：微服务的任务计划管理和任务调度方案。
- ......

当前，Spring Cloud 以 Nitflix 公司的各套开源组件作为主要组件。当前，Spring Cloud 以 Netflix 公司的隔套开源组件作为主要组件，通过 Spring Boot 的封装，给开发者提供了简单易用的组件。

Spring Cloud 目前是以 Netflix 的组件为基础的，但是 Spring Cloud 未来趋势是去 Netflix 组件，不过即使更换新的组件，其设计思想也是大同小异的，且更换周期也比较长，因此本书还是会讲解 Netflix 的组件。



# Spring Cloud 的版本说明
因为 Spring Cloud 融入了大量的其他企业的开源组件，所以这些组件的版本往往并不一致，不同的组件由不同的公司进行维护。为了统一版本号，Pivotal 团队(Spring Cloud 的开发团队)决定使用伦敦地铁站点名称作为版本号。

![](img\SpringCloud版本号（截止2020年初）.PNG)
(截止本书编写时，2020年初)

在编写本书时，版本已更替到了Greenwich.SR2，其中，Greenwich是伦敦的一个地铁站名，SR2代表的意思是“Service Releases 2”，这里的2代表的是第2个Release版本，它代表着Pivotal团队在发布Greenwich版本后，进行修正的第二个版本。由于Spring Boot已经发展到了2.1.x版本，Spring Cloud也发布到了Greenwich版本，因此本书是基于Spring Boot 2.1.0和Greenwich.RELEASE进行讲解的。
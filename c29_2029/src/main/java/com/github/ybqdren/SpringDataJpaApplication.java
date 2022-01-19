package com.github.ybqdren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/19 10:59
 * @package com.github.ybqdren
 * @description
 **/


@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND) // jpa 方法查询策略配置，一般使用默认的方式即可
@SpringBootApplication //(scanBasePackages = {"org.github.ybqdren"})
public class SpringDataJpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class);
    }
}
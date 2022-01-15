package com.github.ybqdren.merchants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.github.ybqdren.merchants"}) // 指定扫描 jpa 的路径
@EntityScan(basePackages = {"com.github.ybqdren.merchants.entity"})     // 指定扫描实体的路径
@SpringBootApplication
public class MerchantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MerchantsApplication.class, args);
    }
}

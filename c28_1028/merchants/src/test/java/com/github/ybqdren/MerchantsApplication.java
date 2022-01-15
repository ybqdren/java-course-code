package com.github.ybqdren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author zhao wen
 * @since 1.0.0
 * <h1> 测试用例入口 </h1>
 *
 **/

@SpringBootApplication
public class MerchantsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MerchantsApplication.class);
    }
}

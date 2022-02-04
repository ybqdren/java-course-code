package com.github.ybqdren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author zhao wen
 * @since 1.0.0
 **/

@SpringBootApplication(scanBasePackages = {"com.github.ybqdren"})
public class MybatisTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisTestApplication.class);
    }
}

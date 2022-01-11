package org.github.ybqdren;

import org.github.ybqdren.config.MyConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/30 16:06
 * @package org.github.ybqdren
 * @description
 **/

@Configuration(value = "org.github.ybqdren.config.*")
@SpringBootApplication //(scanBasePackages = {"org.github.ybqdren"})
public class SpringBootStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootStartApplication.class);
    }
}

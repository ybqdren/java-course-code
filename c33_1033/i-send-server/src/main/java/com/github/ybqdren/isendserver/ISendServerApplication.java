package com.github.ybqdren.isendserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


//@ComponentScan(basePackages = {"com.github.ybqdren"}) // 扫描所有需要的包，包括一些自用的工具类包所在的路径
@SpringBootApplication
public class ISendServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ISendServerApplication.class, args);
    }

}

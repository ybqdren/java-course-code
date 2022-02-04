package com.github.ybqdren.isendserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * <h1> springboot 启动类 </h1>
 * @author zhao wen
 * @since 0.0.1
 **/

@MapperScan(basePackages = {"com.github.ybqdren.isendserver.dao"})
@SpringBootApplication
public class ISendServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ISendServerApplication.class, args);
    }

}



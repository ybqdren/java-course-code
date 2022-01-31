package com.github.ybqdren.isendserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhao wen
 * @since 1.0.0
 **/

//@MapperScan(basePackages = {"com.github.ybqdren.isendserver.dao"})
@SpringBootApplication
//@ComponentScan(basePackages = {"com.github.ybqdren.isendserver"}) // 扫描所有需要的包，包括一些自用的工具类包所在的路径
public class ISendServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ISendServerApplication.class, args);
    }

}



package com.imooc.springboot.study.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <h1>SpringBoot 的配置</h1>
 * Created by Qinyi.
 */
@Data
@Component
@ConfigurationProperties(prefix = "imooc.springboot")
public class SpringBootConfig {

    private String version;
    private String name;
}

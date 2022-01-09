package com.imooc.springboot.study.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.springboot.study.config.SpringBootConfig;
import com.imooc.springboot.study.vo.Imoocer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <h1>测试 Controller</h1>
 * Created by Qinyi.
 */
@Slf4j
@RestController
@RequestMapping("/springboot")
public class Controller {

    /** SpringBoot Config */
    private final SpringBootConfig springBootConfig;

    /** ObjectMapper */
    private final ObjectMapper mapper;

    @Value("${imooc.springboot.version}")
    private String version;
    @Value("${imooc.springboot.name}")
    private String name;

    @Autowired
    public Controller(SpringBootConfig springBootConfig, ObjectMapper mapper) {
        this.springBootConfig = springBootConfig;
        this.mapper = mapper;
    }

    /**
     * <h1>第一种方式的数据注入</h1>
     * 127.0.0.1:8000/imooc/springboot/conf_inject_1
     * */
    @GetMapping("/conf_inject_1")
    public void firstConfInject() {
        log.info("first conf inject: {}, {}", version, name);
    }

    /**
     * <h1>第二种方式的数据注入</h1>
     * 127.0.0.1:8000/imooc/springboot/conf_inject_2
     * */
    @GetMapping("/conf_inject_2")
    public void secondConfInject() {

        log.info("second conf inject: {}, {}",
                springBootConfig.getVersion(), springBootConfig.getName());
    }

    /**
     * 127.0.0.1:8000/imooc/springboot/jackson
     * */
    @GetMapping("/jackson")
    public Imoocer jackson() throws Exception {

        Imoocer imoocer = Imoocer.builder()
                .name("qinyi")
                .age(19)
                .address("shanghai")
                .registerTime(new Date())
                .build();

        String jsonImoocer = mapper.writeValueAsString(imoocer);
        log.info("imoocer jackson: {}", jsonImoocer);

        return mapper.readValue(jsonImoocer, Imoocer.class);
    }
}

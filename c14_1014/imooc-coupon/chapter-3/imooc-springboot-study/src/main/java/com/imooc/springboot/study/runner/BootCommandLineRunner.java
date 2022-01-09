package com.imooc.springboot.study.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Qinyi.
 */
@Order(1)
@Slf4j
@Component
public class BootCommandLineRunner implements CommandLineRunner {

    /**
     * Callback used to run the bean.
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {

        log.info("This is BootCommandLineRunner...");
    }
}

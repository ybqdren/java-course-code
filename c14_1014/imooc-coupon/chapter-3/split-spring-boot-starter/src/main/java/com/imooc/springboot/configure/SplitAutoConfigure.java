package com.imooc.springboot.configure;

import com.imooc.springboot.service.ISplitService;
import com.imooc.springboot.service.impl.SplitServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Qinyi.
 */
@Configuration
@ConditionalOnClass(value = { ISplitService.class, SplitServiceImpl.class})
public class SplitAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    ISplitService starterService() {
        return new SplitServiceImpl();
    }
}

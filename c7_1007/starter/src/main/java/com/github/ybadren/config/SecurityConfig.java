package com.github.ybadren.config;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/3 19:52
 * @package com.github.ybadren.config
 * @description  认证和授权
 **/

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests(req -> req.mvcMatchers("/api/greeting").hasRole("ADMIN"));  检查一个权限 ADMIN
        http
                .formLogin(Customizer.withDefaults())     // 不检查权限，只检查身份
                .authorizeRequests(req -> req.mvcMatchers("/api/greeting").authenticated());

    }
}

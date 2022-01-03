package com.github.ybadren.config;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/3 19:52
 * @package com.github.ybadren.config
 * @description  认证和授权  spring security 模式为先认证，再授权
 **/

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests(req -> req.mvcMatchers("/api/greeting").hasRole("ADMIN"));  检查一个权限 ADMIN
/*        http
                .formLogin(Customizer.withDefaults())     // 不检查权限，只检查身份
                .authorizeRequests(req -> req.mvcMatchers("/api/greeting").authenticated());*/

          // 函数式配置
/*          http.authorizeRequests(req -> req.antMatchers("/api/**").authenticated())
                  .formLogin(form -> form.disable())
                  .httpBasic(Customizer.withDefaults())
                  .csrf(csrf -> csrf.disable());*/

          // 链式配置
          http
                  .authorizeRequests()
                  .antMatchers("/api/**").hasRole("USER")
                  .anyRequest().authenticated()
                  .and()   // 返回一个配置类
                  .formLogin().loginPage("/login").usernameParameter("username1").and()
                  .httpBasic().realmName("BA");

    }

    // 安全配置
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/api/*");    // 不启动的过滤器链
    }
}

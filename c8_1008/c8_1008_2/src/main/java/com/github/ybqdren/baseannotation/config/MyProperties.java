package com.github.ybqdren.baseannotation.config;

import com.github.ybqdren.baseannotation.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/8 9:43
 * @package com.github.ybqdren.baseannotation.config
 * @description 读取配置文件 my.properties
 *
 * 扩展，xml 方式的 spring < Spring引入多个XML配置文件 >  https://blog.csdn.net/pan_junbiao/article/details/104152617
 **/


/**
 注解 {@link PropertySource}：
        - 相当于 contest:property-placeholder 标签
        - 编写在类上面，作用是加载 properties 配置文件
        属性：
            - value[]：用于指定 properties 文件路径，如果在类路径下，需要写上 classpath
 */

@Configuration
@PropertySource("classpath:my.properties")
public class MyProperties {
    /**
     my.name="Zhao Wen"
     my.language="Chinese"
     my.sex="Female"
     my.work="Software Developmenter"
     my.age=20
     */

    @Value("${my.name}")
    private String myName;

    @Value("${my.language}")
    private String myLanguage;

    @Value("${my.sex}")
    private String mySex;

    @Value("${my.work}")
    private String myWork;

    @Value("${my.age}")
    private String myAge;

    private String password;

    @Bean("myData")
    public User showData(){
        User user = new User();
        user.setName(myName);
        user.setSex(mySex);
        user.setWork(myWork);

        return user;
    }
}

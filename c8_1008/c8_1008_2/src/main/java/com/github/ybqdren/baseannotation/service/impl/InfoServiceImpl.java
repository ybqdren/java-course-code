package com.github.ybqdren.baseannotation.service.impl;

import com.github.ybqdren.baseannotation.pojo.Person;
import com.github.ybqdren.baseannotation.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/6 12:21
 * @package com.github.ybqdren.baseannotation.service.impl
 * @description
 **/

@Service
public class InfoServiceImpl implements InfoService {

    // 默认找的是张晓明
/*    @Autowired(required = false)   // required = false -> 依赖对象可以为 null
    Person person;*/

    /**
     注解 {@link Qualifier}:
        - 是一个 DI 注解 （即依赖注入）
        - 在自动按照类型注入的基础之上，再按照 Bean 的 id 注入
        - 它在给字段注入时不能单独使用，必须要和 {@link Autowired} 一起使用；但是在给方法参数注入时，可以单独使用
     */


    // 点名要找张小明
    @Qualifier(value = "zxm_2")   // @Qualifier 注解一定要和 @Autowire 注解 连用（注解方法参数时可以单独使用），根据 bean 的 id 注入
    @Autowired
    Person person;



    @Override
    public Optional<Person> getAPersonByRange() {
        return Optional.ofNullable(person);
    }
}

package com.github.ybqdren.baseannotation.service.impl;

import com.github.ybqdren.baseannotation.pojo.MyWork;
import com.github.ybqdren.baseannotation.pojo.Person;
import com.github.ybqdren.baseannotation.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/6 12:21
 * @package com.github.ybqdren.baseannotation.service.impl
 * @description
 **/


/**
 注解 {@link Autowired} 、注解 {@link javax.annotation.Resource} 、注解 {@link Inject} 区别：
     - @Autowired 是 Spring 自带的，@Inject 是 JSR330 规范实现的，@Resource 是 JSR250 规范实现的，需要使用不同的包
     - @Autowired 、@Inject 用法基本一致，不同的是 @Autowired 有一个 request 属性
     - @Autowired、@Inject 是默认按照 <类型> 匹配的，@Resource 是按照 <名称> 匹配的
     - @Autowired 如果需要按照名称匹配需要和 @Qualifier 一起使用，@Inject 和 @Name 一起使用
 */


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

    /**
     注解 {@link Inject}：
         - Inject 是 JSR330 中的规范， 要使用 @Inject 注解，需要引入 javax.inject 包
         - 根据类型进行自动装配，如果需要名称进行装配，则需要配合 {@link javax.inject.Named} 使用
         - 可以作用在变量、setter 方法、构造函数上

     */
    @Named(value = "好工作")
    @Inject
    MyWork oneJob;


    @Override
    public Optional<Person> getAPersonByRange() {
        return Optional.ofNullable(person);
    }

    @Override
    public Optional<MyWork> getAJob() {
        return Optional.ofNullable(oneJob);
    }


}

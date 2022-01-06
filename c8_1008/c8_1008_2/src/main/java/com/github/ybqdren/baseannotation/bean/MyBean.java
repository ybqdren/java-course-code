package com.github.ybqdren.baseannotation.bean;

import com.github.ybqdren.baseannotation.pojo.MyWork;
import com.github.ybqdren.baseannotation.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/6 12:16
 * @package com.github.ybqdren.baseannotation.bean
 * @description
 **/

/**
 注解 {@link Component} ：把资源交给 Spring 来管理，相当于是使用 xml 配置 Spring 时，在 xml 中配置了一个 bean
    - value  指定 bean 的 id ，如果不指定 value 属性，默认 bean 的 id 是当前类的类名，首字母小写

 生命周期相关注解（待练习）
    注解 @PostConstruct
    注解 @PreDestroy
 */



@Component
public class MyBean {

    @Bean
    public Person getAPerson(){
        Person person = new Person();
        person.setName("张晓明");
        person.setSex(1);
        return person;
    }


    @Bean(name = "zxm_2")
    public Person getAPersonByOther(){
        Person person = new Person();
        person.setName("张小明");
        person.setSex(1);
        return person;
    }

    /**
     * 注解 {@link Resource} ：
     *      - 是 DI 注解（即 依赖注入注解）
     *      - 属于 J2EE JSR 250 规范的实现。
     *      - 默认按照名字装配（byName），可以通过 @Resouce 的 name 属性指定名称，若没有指定 name
     *        当注解写在字段上时，默认取字段名进行 byName 查找，当找不到与名称匹配的 bean 时才按照类型进行装配
     *
     * <p> 注意：name 属性一旦指定，就会按照名称进行装配。</p>
     *
     *
     * 更推荐使用 @Resource 注解，因为它是属于 J2EE，位于 ｛javax.annotation.Resource｝ 包
     *      减少了与 Spring 的耦合，这样代码看起来会比较优雅。
     *
     *
     * @return
     */
/*    @Resource(name = "person3")
    public Person getAPersonByOther_2(){
        Person person = new Person();
        person.setName("张下明");
        person.setSex(1);
        return person;
    }*/


    /**
     注解 {@link Scope} 改变 Bean 作用范围的注解
        - value ： 指定范围的值，取值：[singleton | prototype | request | session | globalsession]

        默认是： singleton，即 Spring 中 Bean 的默认创建方式就是单例

     */
    // 单例获取一个对象
    @Scope(value = "singleton")
    @Bean(name = "singleBean")
    public Person getOneSinglePerson(){
        return new Person();
    }


    // 找一份糊口工作
    @Bean(value = "糊口工作")
    public MyWork getOneJob(){
        MyWork job = new MyWork();
        job.setSalary(1000);
        job.setWorkName("搬砖");
        return job;
    }

    @Bean(value = "好工作")
    public MyWork getGoodOneJob(){
        MyWork job = new MyWork();
        job.setWorkName("编程");
        job.setSalary(10000000);
        return job;
    }


}

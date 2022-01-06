package com.github.ybqdren.baseannotation.api;

import com.github.ybqdren.baseannotation.pojo.Person;
import com.github.ybqdren.baseannotation.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/6 12:20
 * @package com.github.ybqdren.baseannotation.api
 * @description
 **/


/**
 下面 3 个注解都是针对 {@link org.springframework.stereotype.Component} 注解的衍生，他们的作用和属性都是一摸一样的。
 不过他们可以提供更加的明确的语义化

 注解 {@link Controller}   一般用于表现层的注解，如果想要构建一个 restful 风格的接口，可以直接使用 {@link RestController} 注解
 注解 {@link Service}      一般用于业务层的注解
 注解 {@link Repository}   一般用于持久层的注解
 */


@RequestMapping("/info")
@RestController
public class InfoResource {

    /**
     注解 {@link Autowired}：
        - 是 DI 注解（即 依赖注入 注解）
        - 默认按类型装配 (byType)
        - 是由 {@link org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor} 类实现
        - 是 Spring 自带的注解
        - 默认情况下要求依赖对象必须存在，如果需要允许 null 值，可以设置它的 required 属性为 false，见 {@link com.github.ybqdren.baseannotation.service.impl.InfoServiceImpl}
     */
    @Autowired
    InfoService infoService;

    // 随便抓一个人
    @GetMapping("/person")
    public String getAPersonByRange(){
        return infoService.getAPersonByRange().toString();
    }

}

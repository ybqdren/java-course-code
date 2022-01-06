package com.github.ybqdren.baseannotation.api;

import com.github.ybqdren.baseannotation.pojo.Cat;
import com.github.ybqdren.baseannotation.pojo.Person;
import com.github.ybqdren.baseannotation.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Optional;


/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/6 12:20
 * @package com.github.ybqdren.baseannotation.api
 * @description
 **/


/**
 下面 3 个注解都是针对 {@link org.springframework.stereotype.Component} 注解的衍生，他们的作用和属性都是一摸一样的。
 不过他们可以提供更加的明确的语义化

 三个注解都属于 IOC 注解 （创建对象）

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


    /**
     注解 {@value }：
        - 是一个 DI 注解 （即依赖注入）
        - 从配置文件 properties 或者是 yml 文件中根据 Key 取 Value 值
        - 给基本类型和 String 类型注入值
        - 可以使用占位符获取属性文件（properties 或者是 yml）文件中的值
     */
    @Value("${ybqdren.name}")
    private String myName;

    @Value("${ybqdren.years}")
    private String myYears;

    // 随便抓一个人
    @GetMapping("/person")
    public String getAPersonByRange(){
        return infoService.getAPersonByRange().toString();
    }

    // 找一份好工作
    @GetMapping("/job")
    public String findAJob(){ return infoService.getAJob().toString(); }

    // 俺是谁？
    @GetMapping("/me")
    public String whoAmI(){

        System.out.println(myName + "~~");
        System.out.println(myYears + "~~");

        return "我是 " + myName +
                ", 我今年 "+ myYears + "岁";
    }


    // 和下面的路由共有一个 单例对象，有给对象赋值操作的，先调用
    @GetMapping("/single/1")
    public String getASinglePersonHaveValue(){
        return infoService.getOneSinglePersonHaveValue().toString();
    }

    // 没有给对象赋值，后调用
    @GetMapping("/single/2")
    public String getASinglePersonNoValue(){
        return infoService.getOneSinglePerson().toString();
    }

    @GetMapping("/cat")
    public String getACutCat(){
        Optional<Cat> cat = infoService.getOneCutCat();
        String tmpStr = cat.toString();
        return tmpStr;
    }
}

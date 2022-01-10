package com.github.ybqdren.chapter2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@SpringBootApplication(scanBasePackages = "com.github.ybqdren.chapter2")
@Controller // 标识控制器
@RequestMapping("/chapter2") // 请求前缀
public class Chapter2Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter2Application.class, args);
    }

    /**
     * 1. 获取请求路径的参数
     * 2. 在数据模型中设置一个键为 key 的参数
     * 3. 把视图名称设置为 index
     * 4. 返回 ModelAndView，因为这里返回的视图名称为 index，所以需要在 templates 目录下新建一个视图 index.html 文件
     */

    // HTTP GET 请求，且定义 REST 风格路径和参数
    @GetMapping("/index/{value}")
    public ModelAndView index(ModelAndView mav, @PathVariable("value") String value){
        // 设置数据模型
        mav.getModelMap().addAttribute("key",value);
        // 请求名称，定位到 Thymeleaf 模板
        mav.setViewName("index");
        // 返回 ModelAndView
        return mav;
    }
}

package org.github.ybqdren.api;

import org.github.ybqdren.common.annotation.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wen(Joan) Zhao
 * @time 2021/12/30 17:11
 * @package org.github.ybqdren.api
 * @description
 **/

@RestController
@RequestMapping("/v1/home")
public class HomeController {

    @GetMapping("/")
    public String hello(){
        System.out.println("hello ~");
        return "hello ~";
    }

    @Logger(log = "我来了")
    @GetMapping("/getuser")
    public String getUser(){
        return "user user";
    }
}

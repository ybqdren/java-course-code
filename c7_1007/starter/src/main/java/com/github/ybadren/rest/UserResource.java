package com.github.ybadren.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/3 19:46
 * @package com.github.ybadren.rest
 * @description
 **/

@RestController
@RequestMapping("/api")
public class UserResource {

    @GetMapping("/greeting")
    public String greeting(){
        return "Hello World";
    }
}

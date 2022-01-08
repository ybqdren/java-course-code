package com.github.ybqdren.baseannotation.api;

import com.github.ybqdren.baseannotation.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/8 9:51
 * @package com.github.ybqdren.baseannotation.api
 * @description
 **/

@RestController
@RequestMapping("/user")
public class UserResource {

    @Qualifier(value = "myData")
    @Autowired
    public User user;


    @GetMapping("/info")
    public String getUserInfo(){
        return user.toString();
    }

}

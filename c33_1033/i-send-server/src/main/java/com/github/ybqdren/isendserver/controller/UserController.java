package com.github.ybqdren.isendserver.controller;

import com.github.ybqdren.isendserver.pojo.Users;
import com.github.ybqdren.isendserver.utils.IMoocJSONResult;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhao wen
 * @since 1.0.0
 **/

@RestController
@RequestMapping("u")
public class UserController {
    @PostMapping("/registOrLogin")
    public IMoocJSONResult registOrLogin(@RequestBody Users user){

        // 0.判断用户名和密码不能为空
        if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())){
            return IMoocJSONResult.errorMsg("用户名或密码不能为空 ...");
        }



        return IMoocJSONResult.ok();
    }
}

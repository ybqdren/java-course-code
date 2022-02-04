package com.github.ybqdren.controller;

import lombok.extern.slf4j.Slf4j;
import com.github.ybqdren.pojo.AyUser;
import com.github.ybqdren.service.AyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhao wen
 * @since 1.0.0
 **/

@Slf4j
@RestController
@RequestMapping("/user")
public class AyUserController {

    @Autowired
    private AyUserService ayUserService;

    @GetMapping("/findAll")
    public String findAll(){
        List<AyUser> ayUsers = ayUserService.findAll();
        ayUsers.forEach(ayUser -> { log.info(ayUser.toString());});
        return ayUsers.toString();
    }
}

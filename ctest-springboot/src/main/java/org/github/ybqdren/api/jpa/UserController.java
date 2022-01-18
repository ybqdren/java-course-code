package org.github.ybqdren.api.jpa;

import org.github.ybqdren.Repository.UserRepository;
import org.github.ybqdren.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/18 12:28
 * @package org.github.ybqdren.api
 * @description
 **/



@RestController
@RequestMapping(path = "/demo")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/add")
    public void addNewUser(){
        User user = User.builder()
                .name("ybqdren")
                .email("withzhaowen@126.com")
                .build();

        userRepository.save(user);
    }

    @GetMapping(path = "/all")
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/info/{id}")
    public User findOne(@PathVariable Long id){
        return userRepository.findOne(id);
    }

    @GetMapping(path = "/info")
    public void deleteById(@RequestParam Long id){
        userRepository.deleteById(id);
    }


}

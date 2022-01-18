package org.github.ybqdren.api.jpa;

import org.github.ybqdren.repository.PersonRepository;
import org.github.ybqdren.repository.UserPagingAndSortingRepository;
import org.github.ybqdren.repository.UserRepository;
import org.github.ybqdren.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/18 12:28
 * @package org.github.ybqdren.api
 * @description {@link org.springframework.data.repository.CrudRepository} 实现接口测试
 **/



@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPagingAndSortingRepository userPagingAndSortingRepository;

    @Autowired
    private PersonRepository personRepository;


    // ---------------- CrudRepository 测试 ----------------------------------
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

    /*
           {
                "id": 2,
                "name": "ybqdren",
                "email": "withzhaowen@126.com"
            }
     */
    @GetMapping(path = "/info/{id}")
    public Optional<User> findOne(@PathVariable Long id){
        return userRepository.findById(id);
    }

    @GetMapping(path = "/info")
    public void deleteById(@RequestParam Long id){
        userRepository.deleteById(id);
    }

    // ---------------- PagingAndSortingRepository 测试 ----------------------------------


    // 因为版本差异，方法使用方法有些不同...
/*    *//** 验证排序和分页查询方法 *//*
    @GetMapping(path = "/page")
    public Page<User> getAllUserByPage(){
*//*        List<Sort.Order> sortOrderList = new ArrayList<>();
        sortOrderList.add(new Sort.Order(Sort.Direction.ASC, "name"));
        Page<User> name = userPagingAndSortingRepository.findAll(
                new PageRequest(1, 20, new Sort(sortOrderList)));
        return name;*//*
    }

    *//** 排序查询方法
     * @return*//*
    @GetMapping(path = "/sort")
    public Iterable<User> getAllUserByPage(){
*//*        return userPagingAndSortingRepository.findAll(
                new PageRequest(1, 20, new Sort(new Sort.Order(Sort.Direction.ASC, "name"))));;*//*
    }*/



}

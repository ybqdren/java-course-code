package com.github.ybqdren.springdatajpastartdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Author zhao wen
 * @Version 0.0.1
 * @Date 2022/3/19
 **/


@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = userRepository.save(User.builder()
                .name("jackxxxx")
                .email("1233444@126.com")
                .build());

        Assert.notNull(user);

        List<User> users = userRepository.findAll();
        System.out.println(users);

        Assert.notNull(users);
    }

}

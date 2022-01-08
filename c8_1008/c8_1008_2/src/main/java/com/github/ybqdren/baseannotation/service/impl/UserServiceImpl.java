package com.github.ybqdren.baseannotation.service.impl;

import com.github.ybqdren.baseannotation.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Wen(Joan) Zhao
 * @time 2022/1/8 9:38
 * @package com.github.ybqdren.baseannotation.service.impl
 * @description
 **/


@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl() {
        System.out.println("开始装配 UserServiceImpl 类");
    }

    @Override
    public void save() {
        System.out.println("保存用户   service 实现");
    }
}

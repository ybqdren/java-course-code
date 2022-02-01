package com.github.ybqdren.isendserver.service.impl;

import com.github.ybqdren.isendserver.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zhao wen
 * @since 1.0.0
 **/

@Service
public class UserServiceImpl implements UserService {
    @Override
    public boolean queryUesrnameIsExist(String username) {

        return false;
    }
}

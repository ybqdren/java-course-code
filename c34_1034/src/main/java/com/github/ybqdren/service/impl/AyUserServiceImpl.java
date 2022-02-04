package com.github.ybqdren.service.impl;

import com.github.ybqdren.dao.AyUserDao;
import com.github.ybqdren.pojo.AyUser;
import com.github.ybqdren.service.AyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author zhao wen
 * @since 1.0.0
 **/

@Service
public class AyUserServiceImpl implements AyUserService {

    @Autowired
    private AyUserDao ayUserDao;

    @Override
    public List<AyUser> findAll() {
        return ayUserDao.findAll();
    }
}

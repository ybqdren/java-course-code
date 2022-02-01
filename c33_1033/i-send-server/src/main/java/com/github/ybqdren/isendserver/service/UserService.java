package com.github.ybqdren.isendserver.service;

/**
 * @author zhao wen
 * @since 1.0.0
 **/
public interface UserService {

    /**
     * <h2> 判断用户名是否存在 </h2>
     * @param username
     * @return
     */
    public boolean queryUesrnameIsExist(String username);


}

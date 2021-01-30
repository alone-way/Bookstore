package com.service;

import com.pojo.User;

import java.util.Optional;

/**
 * @Description
 * @Author IceCube
 * @Date 2020/11/5 16:06
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @param user 用户
     * @return 返回登录后的用户信息
     */
    User login(User user);

    /**
     * 注册用户
     *
     * @param user 用户
     * @return 返回注册是否成功
     */
    boolean register(User user);

    /**
     * 检查用户名是否已存在
     *
     * @param username 用户名
     * @return 返回true表示已存在, 返回false表示不存在
     */
    boolean existsUsername(String username);
}

package com.dao;

import com.pojo.User;

/**
 * @author IceCube
 * @date 2020/11/1 20:22
 */
public interface UserDao {
    /**
     * 根据用户名查用户信息
     *
     * @param username 用户名
     * @return 若返回null, 说明该用户不存在
     */
    User queryUserByUsername(String username) ;

    /**
     * 根据用户名和密码查用户信息
     *
     * @param username 用户名
     * @param password 密码
     * @return 若返回null, 说明用户名或密码错误
     */
    User queryUserByUsername(String username, String password) ;

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     * @return 返回受影响的行数, 若返回-1说明保存失败
     */
    int saveUser(User user) ;

}

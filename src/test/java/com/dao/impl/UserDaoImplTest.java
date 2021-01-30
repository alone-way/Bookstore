package com.dao.impl;

import com.dao.UserDao;
import com.pojo.User;
import org.junit.jupiter.api.Test;

/**
 * @author IceCube
 * @date 2020/11/1 20:35
 */
class UserDaoImplTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    void queryUserByUsername() {
        System.out.println(userDao.queryUserByUsername("root"));

    }

    @Test
    void testQueryUserByUsername() {
        System.out.println(userDao.queryUserByUsername("root", "root123"));
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setUsername("kfc");
        user.setPassword("kfc123");
        user.setEmail("kfc@qq.com");
        System.out.println(userDao.saveUser(user));
    }
}
package com.service.impl;

import com.pojo.User;
import com.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description
 * @Author IceCube
 * @Date 2020/11/5 16:22
 */
class UserServiceImplTest {
    UserService userService = new UserServiceImpl();
    @Test
    void login() {
//        userService.login(new User(null, null, null, null));
//        userService.login(new User(null, "root", null, null));
//        userService.login(new User(null, "root", "123", null));
        userService.login(new User(null, "root", "root123", null));
    }

    @Test
    void register() {
//        userService.register(new User(null, "root", "root123", null));
//        userService.register(new User(null, "rack", null, null));
        userService.register(new User(null, "rack", "rack123", null));
    }

    @Test
    void existsUsername() {
        assertTrue(userService.existsUsername("root"));
        assertTrue(userService.existsUsername("kfc"));
        assertFalse(userService.existsUsername("tom"));
    }
}
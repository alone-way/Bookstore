package com.service.impl;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.pojo.User;
import com.service.UserService;

/**
 * @Description
 * @Author IceCube
 * @Date 2020/11/5 16:11
 */
public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public User login(User user) {
        return userDao.queryUserByUsername(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean register(User user) {
        if (existsUsername(user.getUsername())) {
            return false;
        }
        int rows = userDao.saveUser(user);
        return rows != -1;
    }

    @Override
    public boolean existsUsername(String username) {
        return userDao.queryUserByUsername(username) != null;
    }
}

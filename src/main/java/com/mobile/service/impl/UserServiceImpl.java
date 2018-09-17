package com.mobile.service.impl;

import com.mobile.dao.IUserDao;
import com.mobile.pojo.User;
import com.mobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    IUserDao iUserDao;

    @Override
    public User login(String phone, String password) {
        User user = iUserDao.getUserByName(phone);
        return user;
    }

    @Override
    public String register(String username, String password) {
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setCreateTime(new Date());
        iUserDao.insertUser(user);
        return "success";
    }

    @Override
    public User getUserById(int userId) {
        return iUserDao.getUserById(userId);
    }
}

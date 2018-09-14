package com.chatRobot.service.impl;

import com.chatRobot.dao.IUserDao;
import com.chatRobot.pojo.User;
import com.chatRobot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    IUserDao iUserDao;

    @Override
    public User login(String userName, String password) {
        User user = iUserDao.getUserByName(userName);
        return user;
    }

    @Override
    public String register(Integer role, String username, String password) {
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setCreateTime(new Date());
        user.setRole(role);
        iUserDao.insertUser(user);
        return "success";
    }

    @Override
    public User getUserById(int userId) {
        return iUserDao.getUserById(userId);
    }
}

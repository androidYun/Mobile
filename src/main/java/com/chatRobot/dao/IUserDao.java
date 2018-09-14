package com.chatRobot.dao;

import com.chatRobot.pojo.User;

public interface IUserDao {
    User getUserByName(String name);

    User getUserById(int userId);

    Boolean insertUser(User user);
}

package com.chatRobot.service;

import com.chatRobot.pojo.User;

public interface UserService {

    User login(String username, String password);

    User getUserById(int userId);

    String register(Integer role, String username, String password);
}

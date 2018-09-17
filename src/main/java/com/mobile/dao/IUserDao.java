package com.mobile.dao;

import com.mobile.pojo.User;

public interface IUserDao {
    User getUserByName(String name);

    User getUserById(int userId);

    Boolean insertUser(User user);
}

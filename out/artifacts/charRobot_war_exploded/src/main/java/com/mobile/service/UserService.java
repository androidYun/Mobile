package com.mobile.service;

import com.mobile.pojo.User;

public interface UserService {

    User getUserByPhone(String phone);

    User getUserByPhoneAndPassword(String phone, String password);

    User getUserById(int userId);

    String register(String phone, String password);
}

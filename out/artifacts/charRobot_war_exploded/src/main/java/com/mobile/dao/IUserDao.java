package com.mobile.dao;

import com.mobile.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface IUserDao {

    User getUserByName(String phone);

    User getUserByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);

    User getUserById(int userId);

    Boolean insertUser(User user);
}

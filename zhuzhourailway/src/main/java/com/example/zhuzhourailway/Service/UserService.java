package com.example.zhuzhourailway.Service;

import com.example.zhuzhourailway.Model.Pojo.User;

import java.util.List;

public interface UserService {
    List<User> selectall();
    User login(int usernumber);
    int adduser(User user);

}

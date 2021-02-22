package com.example.zhuzhourailway.Service.ServiceImpl;

import com.example.zhuzhourailway.Model.Dao.UserMapper;
import com.example.zhuzhourailway.Model.Pojo.User;
import com.example.zhuzhourailway.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> selectall() {
        List<User> userList= userMapper.selectall();
        return userList;
    }

    @Override
    public User login(int usernumber) {
        return userMapper.login(usernumber);
    }

    @Override
    public int adduser(User user) {
        return userMapper.adduser(user);
    }
}

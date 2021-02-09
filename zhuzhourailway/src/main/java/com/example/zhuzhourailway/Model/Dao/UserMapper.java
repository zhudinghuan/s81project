package com.example.zhuzhourailway.Model.Dao;

import com.example.zhuzhourailway.Model.Pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    List <User> selectall();
    User login(int usernumber);
    int adduser(User user);
}

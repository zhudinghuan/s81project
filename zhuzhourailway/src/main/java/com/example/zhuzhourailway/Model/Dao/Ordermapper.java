package com.example.zhuzhourailway.Model.Dao;

import com.example.zhuzhourailway.Model.Pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Ordermapper {
    int addOrder(Order order);
    int updateOrder(String orderid);
    Order selectOrderbyOuttradeno(String orderid);
    List <Order> selectAllOrder();
    Order selectorderbyid(String orderid);
}

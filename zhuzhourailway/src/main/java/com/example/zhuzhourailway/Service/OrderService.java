package com.example.zhuzhourailway.Service;

import com.example.zhuzhourailway.Model.Pojo.Order;

import java.util.List;


public interface OrderService {
    int addOrder(Order order);
    int updateOrder(String orderid);
    Order selectOrderbyOuttradeno(String orderid);
    List<Order> selectAllOrder();
    Order selectorderbyid(String orderid);

}

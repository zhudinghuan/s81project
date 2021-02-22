package com.example.zhuzhourailway.Service;

import com.example.zhuzhourailway.Model.Pojo.Order;


public interface OrderService {
    int addOrder(Order order);
    int updateOrder(String orderid);
    Order selectOrderbyOuttradeno(String orderid);
}

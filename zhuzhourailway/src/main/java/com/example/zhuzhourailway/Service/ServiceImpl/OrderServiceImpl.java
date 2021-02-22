package com.example.zhuzhourailway.Service.ServiceImpl;

import com.example.zhuzhourailway.Model.Dao.Ordermapper;
import com.example.zhuzhourailway.Model.Pojo.Order;
import com.example.zhuzhourailway.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    Ordermapper ordermapper;
    @Override
    public int addOrder(Order order) {
        return ordermapper.addOrder(order);
    }

    @Override
    public int updateOrder(String orderid) {
        return ordermapper.updateOrder(orderid);
    }

    @Override
    public Order selectOrderbyOuttradeno(String orderid) {
        return ordermapper.selectOrderbyOuttradeno(orderid);
    }
}

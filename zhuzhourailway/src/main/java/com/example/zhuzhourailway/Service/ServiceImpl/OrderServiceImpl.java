package com.example.zhuzhourailway.Service.ServiceImpl;

import com.example.zhuzhourailway.Model.Dao.Ordermapper;
import com.example.zhuzhourailway.Model.Pojo.Order;
import com.example.zhuzhourailway.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Order> selectAllOrder() {
        return ordermapper.selectAllOrder();
    }

    @Override
    public Order selectorderbyid(String orderid) {
        return ordermapper.selectorderbyid(orderid);
    }
}

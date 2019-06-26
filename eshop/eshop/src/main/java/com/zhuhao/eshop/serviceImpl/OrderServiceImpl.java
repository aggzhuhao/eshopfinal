package com.zhuhao.eshop.serviceImpl;

import com.zhuhao.eshop.entity.Order;
import com.zhuhao.eshop.entity.User;
import com.zhuhao.eshop.mapper.OrderMapper;
import com.zhuhao.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public int insertOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    @Override
    public List<Order> getOrderlistAjaxlimit(Order order) {
        return orderMapper.getOrderlistAjaxlimit(order);
    }

    public int getOrderlistAjaxCount(Order order){
        return orderMapper.getOrderlistAjaxCount(order);
    }

    @Override
    public Order getorderdetailById(Order order) {
        return orderMapper.getorderdetailById(order);
    }

    @Override
    public List<Order> selectorders(User user) {
        return orderMapper.selectorders(user);
    }
}

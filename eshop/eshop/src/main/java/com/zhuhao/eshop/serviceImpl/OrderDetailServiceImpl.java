package com.zhuhao.eshop.serviceImpl;


import com.zhuhao.eshop.entity.Order;
import com.zhuhao.eshop.entity.OrderDetail;
import com.zhuhao.eshop.mapper.OrderDetailMapper;
import com.zhuhao.eshop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailMapper orderDetailMapper;


    @Override
    public int insertOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.insertOrderDetail(orderDetail);
    }

    @Override
    public List<OrderDetail> selectorderdetailByorderId(Order order) {
        return orderDetailMapper.selectorderdetailByorderId(order);
    }
}

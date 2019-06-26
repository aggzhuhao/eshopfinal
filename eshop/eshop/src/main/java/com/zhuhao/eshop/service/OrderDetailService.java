package com.zhuhao.eshop.service;

import com.zhuhao.eshop.entity.Order;
import com.zhuhao.eshop.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    int insertOrderDetail(OrderDetail orderDetail);

    /*
     * 根据订单Id查询订单明细 detail表
     */
    List<OrderDetail> selectorderdetailByorderId(Order order);
}

package com.zhuhao.eshop.service;

import com.zhuhao.eshop.entity.Order;
import com.zhuhao.eshop.entity.OrderDetail;
import com.zhuhao.eshop.entity.User;

import java.util.List;

public interface OrderService {
    /*
     * 插入一条订单
     */
    int insertOrder(Order order);
    /*
     * 订单分页
     */
    List<Order> getOrderlistAjaxlimit(Order order);

    /*
     * 查询订单总数By userid
     */
    int getOrderlistAjaxCount(Order order);

    /*
     * 根据订单Id查询订单详情
     */
    Order getorderdetailById(Order order);

    List<Order> selectorders(User user);
}

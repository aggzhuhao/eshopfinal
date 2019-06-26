package com.zhuhao.eshop.mapper;

import com.zhuhao.eshop.entity.Order;
import com.zhuhao.eshop.entity.OrderDetail;
import com.zhuhao.eshop.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * mybatis mapper --> OrderMapper.xml
 * 为service层 提供数据
 */
@Repository
public interface OrderMapper {
    /*
     * 插入一条订单
     */
    @Transactional
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
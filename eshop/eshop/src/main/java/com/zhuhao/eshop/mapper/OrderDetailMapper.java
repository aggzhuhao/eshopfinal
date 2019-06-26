package com.zhuhao.eshop.mapper;

import com.zhuhao.eshop.entity.Order;
import com.zhuhao.eshop.entity.OrderDetail;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
public interface OrderDetailMapper {
    /*
     * 插入一条订单明细
     */
    @Transactional
    int insertOrderDetail(OrderDetail orderDetail);

    /*
     * 根据订单Id查询订单明细 detail表
     */
    List<OrderDetail> selectorderdetailByorderId(Order order);
}
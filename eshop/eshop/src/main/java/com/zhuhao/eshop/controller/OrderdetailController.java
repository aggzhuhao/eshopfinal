package com.zhuhao.eshop.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.zhuhao.eshop.entity.Order;
import com.zhuhao.eshop.entity.OrderDetail;
import com.zhuhao.eshop.serviceImpl.OrderDetailServiceImpl;
import com.zhuhao.eshop.serviceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class OrderdetailController {
    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    OrderDetailServiceImpl orderDetailService;

    @RequestMapping("/orderdetail")
    public String orderdetail(){ return "orderdetail";}

    @RequestMapping("/getorderById/{orderid}")
    public String getorderdetailById(@PathVariable(value = "orderid",required = true)String orderid
    , HttpSession session){
        Order order1 = new Order();
        order1.setOrderId(Integer.parseInt(orderid));
        Order order = orderService.getorderdetailById(order1);
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setCreatTimestr(s.format(order.getCreateTime()));
        session.setAttribute("order",order);
        return "redirect:/orderdetail";
    }
    @ResponseBody
    @RequestMapping("/orderdetailload")
    public List<OrderDetail> orderdetailload(HttpSession session){
        Order order = (Order) session.getAttribute("order");
        return orderDetailService.selectorderdetailByorderId(order);
    }
}

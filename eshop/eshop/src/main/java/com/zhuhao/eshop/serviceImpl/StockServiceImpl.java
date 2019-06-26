package com.zhuhao.eshop.serviceImpl;

import com.zhuhao.eshop.entity.*;
import com.zhuhao.eshop.mapper.OrderDetailMapper;
import com.zhuhao.eshop.mapper.OrderMapper;
import com.zhuhao.eshop.mapper.ShoppingCartMapper;
import com.zhuhao.eshop.mapper.StockMapper;
import com.zhuhao.eshop.service.ProductService;
import com.zhuhao.eshop.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;

@Service
public class StockServiceImpl implements StockService {
    private static final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);
    @Autowired
    StockMapper stockMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderDetailMapper orderDetailMapper;
    @Autowired
    ShoppingCartMapper shoppingCartMapper;


    @Override
    public Stock selectStockByPId(Product product) {
        return stockMapper.selectStockByPId(product);
    }

    @Override
    public int updateStockByproductId(Product product) {
        return stockMapper.updateStockByproductId(product);
    }

    @Override
    public int insertstockp(Stock stock) {
        return stockMapper.insertstockp(stock);
    }

    @Override
    public int deletestock(Stock stock) {
        return stockMapper.deletestock(stock);
    }

    @Override
    public int updatestock(Stock stock) {
        return stockMapper.updatestock(stock);
    }

    //订单
    @Transactional
    public int jiesuanproduct(Order order, String productidandquantity, User user){
        try {
            orderMapper.insertOrder(order);
            String[] productstrarr = productidandquantity.split("-");
            //若不为一件物品，则直接清空购物车
            System.out.println(productstrarr);
            System.out.println(productstrarr.length);
            if(productstrarr.length > 1){
                log.info("用户："+user.getUsername() +" 的购物车清空成功");
                shoppingCartMapper.removeShoppingcartByUserId(user);
            }
            for(int i = 0 ;i < productstrarr.length;i++){
                //0为productid ；1为数量
                String[] productstr =  productstrarr[i].split(",");
                String productid = (productstr[0].split(":"))[1];
                String number = (productstr[1].split(":"))[1];
                Product product = new Product();
                product.setProductId(Integer.parseInt(productid));
                product.setCategoryId(Integer.parseInt(number));
                updateStockByproductId(product);
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(order.getOrderId());
                orderDetail.setProductId(Integer.parseInt(productid));
                orderDetail.setQuantity(Integer.parseInt(number));

                orderDetailMapper.insertOrderDetail(orderDetail);

            }

        }catch (Exception e){
            log.error("用户："+user.getUsername()+" 结算失败；");
            return 0;
        }
        return 1;
    }
}

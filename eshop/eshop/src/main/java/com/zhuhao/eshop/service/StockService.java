package com.zhuhao.eshop.service;

import com.zhuhao.eshop.entity.Product;
import com.zhuhao.eshop.entity.Stock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface StockService {
    /*
     * 根据商品ID 查询库存信息
     */
    Stock selectStockByPId(Product product);

    /*
     * 减少对应商品的库存
     */
    int updateStockByproductId(Product product);

    //插入
    int insertstockp(Stock stock);

    int deletestock(Stock stock);

    //修改
    int updatestock(Stock stock);
}

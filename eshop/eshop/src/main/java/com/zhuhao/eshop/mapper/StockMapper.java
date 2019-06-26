package com.zhuhao.eshop.mapper;

import com.zhuhao.eshop.entity.Product;
import com.zhuhao.eshop.entity.Stock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * mybatis mapper --> UserMapper.xml
 * 为service层 提供数据
 */
@Repository
public interface StockMapper {
    /*
     * 根据商品ID 查询库存信息
     */
    Stock selectStockByPId(Product product);

    /*
     * 减少对应商品的库存
     */
    @Transactional
    int updateStockByproductId(Product product);

    //插入
    @Transactional
    int insertstockp(Stock stock);

    //删除
    @Transactional
    int deletestock(Stock stock);

    //修改
    @Transactional
    int updatestock(Stock stock);

}
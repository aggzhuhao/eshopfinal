package com.zhuhao.eshop.service;

import com.zhuhao.eshop.entity.Product;
import com.zhuhao.eshop.entity.Type;

import java.util.List;

public interface Category {
    /*
     * 查询所有的产品的类型
     */
    List<com.zhuhao.eshop.entity.Category> selectAllCategory();
    /*
     * 根据种类的编号 查询所有该种类的产品
     */
    List<Product> selectproductByCategoryId(int categoryId);
    /*
     * 根据种类的编号 查询该种类的名称
     */
    com.zhuhao.eshop.entity.Category selectBycategoryId(int categoryId);

    //插入
    int insertcategory(com.zhuhao.eshop.entity.Category category);


    //根据type id 查询 category
    List<com.zhuhao.eshop.entity.Category> getcategoryname(Type type);
}

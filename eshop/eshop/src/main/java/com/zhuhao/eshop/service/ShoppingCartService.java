package com.zhuhao.eshop.service;

import com.zhuhao.eshop.entity.ShoppingCart;
import com.zhuhao.eshop.entity.User;

import java.util.List;

public interface ShoppingCartService {
    int joinshopingcart(ShoppingCart shoppingCart);

    List<ShoppingCart> selectcartByUsername(User user);

    int updateShopcartByCartId(ShoppingCart shoppingCart);

    int removeShoppingcartByID(ShoppingCart shoppingCart);

    int removeShoppingcartByUserId(User user);
}
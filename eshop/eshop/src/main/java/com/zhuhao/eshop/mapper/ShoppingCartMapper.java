package com.zhuhao.eshop.mapper;

import com.zhuhao.eshop.entity.ShoppingCart;
import com.zhuhao.eshop.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartMapper {
    int joinshopingcart(ShoppingCart shoppingCart);

    List<ShoppingCart> selectcartByUsername(User user);

    int updateShopcartByCartId(ShoppingCart shoppingCart);

    int removeShoppingcartByID(ShoppingCart shoppingCart);

    int removeShoppingcartByUserId(User user);
}
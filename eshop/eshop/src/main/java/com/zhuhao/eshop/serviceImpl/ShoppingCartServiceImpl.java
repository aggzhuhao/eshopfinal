package com.zhuhao.eshop.serviceImpl;

import com.zhuhao.eshop.entity.ShoppingCart;
import com.zhuhao.eshop.entity.User;
import com.zhuhao.eshop.mapper.ShoppingCartMapper;
import com.zhuhao.eshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Override
    public int joinshopingcart(ShoppingCart shoppingCart) {
        return shoppingCartMapper.joinshopingcart(shoppingCart);
    }

    @Override
    public List<ShoppingCart> selectcartByUsername(User user) {
        return shoppingCartMapper.selectcartByUsername(user);
    }

    @Override
    public int updateShopcartByCartId(ShoppingCart shoppingCart) {
        return shoppingCartMapper.updateShopcartByCartId(shoppingCart);
    }

    @Override
    public int removeShoppingcartByID(ShoppingCart shoppingCart) {
        return shoppingCartMapper.removeShoppingcartByID(shoppingCart);
    }

    @Override
    public int removeShoppingcartByUserId(User user) {
        return shoppingCartMapper.removeShoppingcartByUserId(user);
    }
}

package com.zhuhao.eshop.mapper;

import com.zhuhao.eshop.entity.Category;
import com.zhuhao.eshop.entity.Product;
import com.zhuhao.eshop.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * mybatis mapper --> UserMapper.xml
 * 为service层 提供数据
 */
@Repository
public interface ProductMapper {
    /*
     * 查询所有的产品
     */
    List<Product> selectAllProduct();

    /*
     * 首页查询前10 条数据
     */
    List<Product> selectProductLimit();
    /*
     * 根据产品的ID查询该产品的详细信息
     */
    Product selectProductById(Product product);
    /*
     * 模糊查询有的关键字的产品
     */
    List<Product> selectlikeProduct(String description);

    /*
     * 查询购物车内的商品详情和库存
     */
    List<Product> selectAllshoppingCartInproduct(User user);

    //差新品
    List<Product> selectnewproduct();

    //打折商品
    List<Product> selectdiscountproduct();

    //插入商品
    @Transactional
    int insertproduct(Product product);
    //delete商品
    @Transactional
    int deleteproduct(Product product);
    //修改商品
    @Transactional
    int updateproduct(Product product);

    List<Product> selecttuijianproduct(Category category);
}
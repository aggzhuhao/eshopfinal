package com.zhuhao.eshop.serviceImpl;

import com.zhuhao.eshop.entity.Product;
import com.zhuhao.eshop.entity.Type;
import com.zhuhao.eshop.mapper.CategoryMapper;
import com.zhuhao.eshop.service.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImpl implements Category {
    @Autowired
    CategoryMapper categoryMapper;

    public List<com.zhuhao.eshop.entity.Category> selectAllCategory() {
        return categoryMapper.selectAllCategory();
    }

    @Override
    public List<Product> selectproductByCategoryId(int categoryId) {
        return categoryMapper.selectproductByCategoryId(categoryId);
    }

    @Override
    public com.zhuhao.eshop.entity.Category selectBycategoryId(int categoryId) {
        return categoryMapper.selectBycategoryId(categoryId);
    }

    @Override
    public int insertcategory(com.zhuhao.eshop.entity.Category category) {
        return categoryMapper.insertcategory(category);
    }

    @Override
    public List<com.zhuhao.eshop.entity.Category> getcategoryname(Type type) {
        return categoryMapper.getcategoryname(type);
    }
}

package com.zhuhao.eshop.service;

import com.zhuhao.eshop.entity.Category;
import com.zhuhao.eshop.entity.Type;

import java.util.List;

public interface TypeService {
    //查询所有的种类
    public List<Type> selectAllType();

    //直接查询
    List<Type>  selectAllTypeNobyCid();

    //插入
    int inserttype(Type type);

    List<Type> selecttypeidbycid(Category category);
}

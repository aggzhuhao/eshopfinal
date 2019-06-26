package com.zhuhao.eshop.serviceImpl;

import com.zhuhao.eshop.entity.Category;
import com.zhuhao.eshop.entity.Type;
import com.zhuhao.eshop.mapper.TypeMapper;
import com.zhuhao.eshop.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    TypeMapper typeMapper;

    public List<Type> selectAllType() {
        return typeMapper.selectAllType();
    }

    @Override
    public List<Type> selectAllTypeNobyCid() {
        return typeMapper.selectAllTypeNobyCid();
    }

    @Override
    public int inserttype(Type type) {
        return typeMapper.inserttype(type);
    }

    @Override
    public List<Type> selecttypeidbycid(Category category) {
        return typeMapper.selecttypeidbycid(category);
    }


}

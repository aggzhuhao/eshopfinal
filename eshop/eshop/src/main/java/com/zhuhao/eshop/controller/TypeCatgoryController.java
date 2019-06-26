package com.zhuhao.eshop.controller;

import com.zhuhao.eshop.entity.Category;
import com.zhuhao.eshop.entity.Type;
import com.zhuhao.eshop.service.TypeService;
import com.zhuhao.eshop.serviceImpl.CategoryImpl;
import com.zhuhao.eshop.serviceImpl.TypeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TypeCatgoryController {

    private final static Logger log = LoggerFactory.getLogger(TypeCatgoryController.class);

    @Autowired
    TypeServiceImpl typeService;

    @Autowired
    CategoryImpl categoryservice;

    @RequestMapping("inserttypet")
    public String inserttypet(HttpSession session){
        return "inserttypet";
    }

    @RequestMapping("insertcategory")
    public String insertcategory(HttpSession session){
        List<Type> typelistinsert = typeService.selectAllTypeNobyCid();
        session.setAttribute("typelistinsert",typelistinsert);
        return "insertcategory";
    }

    @ResponseBody
    @RequestMapping("inserttypetaction")
    public int inserttypetaction(String typename){
        Type type = new Type();
        type.setName(typename);
        int a = typeService.inserttype(type);
        if(a != 0 ){
            log.info("插入一条分类："+ typename);
        }else{
            log.error("插入分类失败");
        }
        return a;
    }

    @ResponseBody
    @RequestMapping("insertcategoryaction")
    public int insertcategoryaction(String cname,String typeid){
        Category category = new Category();
        category.setName(cname);
        category.setTypeId(Integer.parseInt(typeid));
        int a = categoryservice.insertcategory(category);
        if(a != 0 ){
            log.info("插入一条类型："+ cname+" ,类型分类编号为：" +typeid);
        }else{
            log.error("插入类型失败");
        }
        return a;
    }

}

package com.zhuhao.eshop.controller;

import com.zhuhao.eshop.entity.Category;
import com.zhuhao.eshop.entity.Type;
import com.zhuhao.eshop.serviceImpl.CategoryImpl;
import com.zhuhao.eshop.serviceImpl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    CategoryImpl categoryService;

    @Autowired
    TypeServiceImpl typeService;


    @RequestMapping("/categorylist")
    public String categorylist(HttpSession session) {
        session.setAttribute("typelist",typeService.selectAllType());
        return "categorylist";
    }

    @RequestMapping("/categorylistadmin")
    public String categorylistadmin(HttpSession session) {
        session.setAttribute("typelist",typeService.selectAllType());
        return "categorylistadmin";
    }

    @RequestMapping("/selectCategoryId/{categoryId}")
    public String selectCategoryId(@PathVariable(value = "categoryId",required = false) int categoryId,HttpSession session){
        session.setAttribute("productlist",categoryService.selectproductByCategoryId(categoryId));
        return "redirect:/categorylist";
    }

    @RequestMapping("/selectCategoryIdadmin/{categoryId}")
    public String selectCategoryIdadmin(@PathVariable(value = "categoryId",required = false) int categoryId,HttpSession session){
        session.setAttribute("productlist",categoryService.selectproductByCategoryId(categoryId));
        return "redirect:/categorylistadmin";
    }

    @ResponseBody
    @RequestMapping("/getcategoryname")
    public List<Category> getcategoryname(String typeId){
        Type type = new Type();
        type.setTypeId(Integer.parseInt(typeId  ));
        return categoryService.getcategoryname(type);
    }
}

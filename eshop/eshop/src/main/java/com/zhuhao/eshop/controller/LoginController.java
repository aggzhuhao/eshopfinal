package com.zhuhao.eshop.controller;


import com.zhuhao.eshop.entity.User;
import com.zhuhao.eshop.service.TypeService;
import com.zhuhao.eshop.serviceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

/**
 * restful 接口
 */
@Controller
public class LoginController {

    @Autowired
    LoginServiceImpl loginService;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    StockServiceImpl stockService;

    @Autowired
    CategoryImpl category;

    @Autowired
    TypeService typeService;

    @Autowired
    UserCenterServImpl userCenterServ;

    //管理员
    @RequestMapping("/adminlogin")
    public String adminlogin(){
        return "adminlogin";
    }

    @RequestMapping("/adminindex")
    public String adminindex(HttpSession session){
        session.setAttribute("productlist", productService.selectProductLimit());
        session.setAttribute("categorylist",category.selectAllCategory());
        session.setAttribute("typelist",typeService.selectAllType());
        return "adminindex";
    }

    //管理员登录
    @RequestMapping("/loginadmin")
    public String loginadmin(User user,HttpSession session,RedirectAttributes attributes){
        User user1 = loginService.selectUserByIdPsw(user);
        if(user1 != null){
            if(user1.getAdmin() == 1){
                session.setAttribute("admin",user1);
                return "redirect:/adminindex";
            }
        }
        attributes.addFlashAttribute("errorMsg","管理员账号密码不正确！");
        return "redirect:/adminlogin";
    }

    //判断账号是否能登录
    @RequestMapping("/loginuser")
    public Object getuser(User user, HttpSession session,RedirectAttributes attributes){
        User user1 = loginService.selectUserByIdPsw(user);
        if(user1 != null){
            session.setAttribute("user",user1);
            return "redirect:/index";
        }
        attributes.addFlashAttribute("errorMsg","账号或密码不正确！");
        return "redirect:/login";
    }

    //ajax  判断数据库内是否存在user
    @ResponseBody
    @RequestMapping("/checkusername")
    public boolean checkusername(String username){
        return loginService.checkusername(username);
    }

    //ajax  判断数据库内是否存在user
    @ResponseBody
    @RequestMapping("/checkemail")
    public boolean checkemail(String email){
        User user = userCenterServ.checkemail(email);
        if(user != null){
            return false;
        }
        return true;
    }

    //新增用户
    @RequestMapping("/createUser")
    public String createUser(User user, HttpSession session){
        if(user != null && user.getUsername() != null) {
            if (loginService.createUser(user)) {
                session.setAttribute("user", user);
                return "redirect:/index";
            }
            return "register";
        }
        return "index";
    }

    //注销用户
    @RequestMapping("/removeuser")
    public String removeuser(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/index";
    }

    //注销管理员
    @RequestMapping("/removeadmin")
    public String removeadmin(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/adminlogin";
    }

    //用于拦截器
    @RequestMapping(value = "/totologin")
    public  String totologin(RedirectAttributes attributes){
        attributes.addFlashAttribute("errorMsg","请先登录再进行操作！");
        return "redirect:/login";
    }

    //用于拦截器
    @RequestMapping(value = "/totoadminlogin")
    public  String totoadminlogin(RedirectAttributes attributes){
        attributes.addFlashAttribute("errorMsg","请先登录再进行操作！");
        return "redirect:/adminlogin";
    }
}

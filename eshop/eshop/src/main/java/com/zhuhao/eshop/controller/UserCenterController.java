package com.zhuhao.eshop.controller;

import com.zhuhao.eshop.configure.SaveCheckcodeThread;
import com.zhuhao.eshop.entity.User;
import com.zhuhao.eshop.serviceImpl.UserCenterServImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@Controller
public class UserCenterController {
    private  static  final Logger log = LoggerFactory.getLogger(UserCenterController.class);

    @Autowired
    UserCenterServImpl userCenterServ;

    @RequestMapping("/article/{param}")
    public String toregister(@PathVariable(value = "param",required = false)String param, HttpSession session){
        if(param != null && !"".equals(param)){
            if("xieyi".equals(param)){
                session.setAttribute("param","用户协议");
            }else if("zhuceyonghu".equals(param)){
                session.setAttribute("param","注册新用户");
            }else if("xiugaixinxi".equals(param)){
                session.setAttribute("param","修改信息");
            }else if("gouwuliucheng".equals(param)){
                session.setAttribute("param","购物流程");
            }else if("gouwucheshuoming".equals(param)){
                session.setAttribute("param","购物车说明");
            }
        }
        return "redirect:/article";
    }

    /*@ResponseBody
    @RequestMapping("/sendEmail")
    public void */

    @RequestMapping("/forgetPw")
    public String forgetPw(){
        return "forgetPw";
    }

  /*  @ResponseBody
    @RequestMapping("/getusername")
    public String getusername(HttpSession session){
        User user = (User) session.getAttribute("user");
        return user.getUsername();
    }*/

    @ResponseBody
    @RequestMapping("/checkuserpassword")
    public int checkuserpassword(String password,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user == null){
            return 3;
        }
        if(password.equals(user.getPassword())){
            return 1;
        }
        return 2;
    }

    @RequestMapping("/updateUserpassword/{value01}")
    public String updateUserpassword(@PathVariable(value = "value01")String value01,HttpSession session){
        User user = (User) session.getAttribute("user");
        user.setPassword(value01);
        userCenterServ.updateUserpassword(user);
        session.removeAttribute("user");
        return "redirect:/login";
    }
    @ResponseBody
    @RequestMapping("/sendEmail")
    public boolean sendEmail(String emailstr,HttpSession session) throws MessagingException {
        int a = (int)((Math.random()*9+1)*1000);
        String checkcode = a +"";
        if(userCenterServ.sendEmailByzh(checkcode,emailstr)){
            SaveCheckcodeThread thread = new SaveCheckcodeThread(checkcode,session);
            thread.run();
            return true;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping("/checkmailCode")
    public boolean checkmailCode(String checkcode,HttpSession session) throws MessagingException {
        String codemail = (String) session.getAttribute("checkcodefogetPW");
        if(!"".equals(codemail) && null != codemail){
            if(codemail.equals(checkcode)){
                return true;
            }
        }
        return false;
    }

    @ResponseBody
    @RequestMapping("/forgetmailpassword")
    public boolean forgetmailpassword(String password,String mail,HttpSession session) throws MessagingException {
        User user = new User();
        user.setPassword(password);
        user.setEmail(mail);
        if(userCenterServ.forgetmailpassword(user) != 0){
            return true;
        }
        return false;
    }

}

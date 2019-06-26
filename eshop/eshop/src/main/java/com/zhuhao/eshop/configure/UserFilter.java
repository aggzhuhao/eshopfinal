
package com.zhuhao.eshop.configure;

import com.zhuhao.eshop.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*@Component
@WebFilter(urlPatterns = {"/joinshopingcart/*"})*/
public class UserFilter implements Filter {
    private final static Logger log = LoggerFactory.getLogger(UserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //log.info("USER 用户拦截器 MyFiltery已被初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       // HttpServletRequest
        /*log.info("启动拦截器————");
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null){
            filterChain.doFilter(request,response);
        }else {
            response.sendRedirect("/login");
        }*/

    }
}


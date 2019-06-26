package com.zhuhao.eshop.configure;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 定义视图解析，用于解析无需任何数据交互的，直接到页面
 */
@Configuration  //表示这是一个配置类
public class MyMvcConfigure implements WebMvcConfigurer {
    /*
     * 添加视图解析器
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/article").setViewName("article");
        registry.addViewController("/cart").setViewName("cart");

        registry.addViewController("/checkbuy").setViewName("checkbuy");
        registry.addViewController("/detail").setViewName("detail");
        registry.addViewController("/discount").setViewName("discount");

        //registry.addViewController("/").setViewName("index");
        //registry.addViewController("/").setViewName("index");
        registry.addViewController("/lists").setViewName("lists");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/orderlist").setViewName("orderlist");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/shownew").setViewName("shownew");
        registry.addViewController("/usercenter").setViewName("usercenter");


    }

    //配置静态资源访问
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/images/**").addResourceLocations("file:/root/eshop.jar/BOOT-INF/classes/static/images");
     registry.addResourceHandler("/images/**").addResourceLocations("file:D:/idespace/eshop/src/main/resources/static/images/");
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor())    //指定拦截器类
                .addPathPatterns("/joinshopingcart/**")        //指定该类拦截的url
                .addPathPatterns("/nowBuyproduct/**")
                .addPathPatterns("/orderlist/**")
                .addPathPatterns("/orderdetail/**");
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/changeproductaction/**")
                .addPathPatterns("/adminindex/**")
                .addPathPatterns("/categorylistadmin/**")
                .addPathPatterns("/insertproduct/**")
                .addPathPatterns("/insertcategory/**");

    }

    }

package com.zhuhao.eshop.controller;

import com.zhuhao.eshop.entity.Product;
import com.zhuhao.eshop.entity.ShoppingCart;
import com.zhuhao.eshop.entity.User;
import com.zhuhao.eshop.service.ProductService;
import com.zhuhao.eshop.serviceImpl.ProductServiceImpl;
import com.zhuhao.eshop.serviceImpl.ShoppingCartServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShopppingCartController {
    private static final Logger log = LoggerFactory.getLogger(ShoppingCart.class);

    public static String productidandquantitytrue = "";

    @Autowired
    ShoppingCartServiceImpl shoppingCartService;

    @Autowired
    ProductServiceImpl productService;
    @RequestMapping(value = "/joinshopingcart/{number}/{productid}")
    public String joinshopingcart(@PathVariable(value = "number")Integer number,
                                  @PathVariable(value = "productid")Integer productid,
                                  HttpSession session){

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setProductId(productid);
        shoppingCart.setQuantity(number);
        String username = ((User)session.getAttribute("user")).getUsername();
        /*User user = (User)session.getAttribute("user");
        List<ShoppingCart> cartslists = shoppingCartService.selectcartByUsername(user);
        //如果是购物车内已经有的 是否需要添加数量，而不是添加商品
        boolean result = false;*/

        shoppingCart.setUsername(username);
        int i =shoppingCartService.joinshopingcart(shoppingCart);
        if(i != 0){
            log.info("用户："+username+" 加入商品："+productid+" 数量为："+ number+"购物车成功");
            User user = (User) session.getAttribute("user");
            List<Product> cartlist = productService.selectAllshoppingCartInproduct(user);
            log.info("用户："+username+" 查询购物车内所有商品");
            session.setAttribute("cartlist",cartlist);
        }else{
            log.info("用户："+username+" 加入购物车失败");
        }
        return "redirect:/cart";
    }
    @RequestMapping(value = "/tocart")
    public String tocart(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Product> cartlist = productService.selectAllshoppingCartInproduct(user);
        log.info("用户："+user.getUsername()+" 查询购物车内所有商品");
        session.setAttribute("cartlist",cartlist);
        return "redirect:/cart";
    }

    @ResponseBody
    @RequestMapping(value = "/updateShopcartByCartId")
    public int updateShopcartByCartId(String cartnumber,String shoppingcartid){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setQuantity(Integer.parseInt(cartnumber));
        shoppingCart.setShoppingCartId(Integer.parseInt(shoppingcartid));
        int i = shoppingCartService.updateShopcartByCartId(shoppingCart);
        return i;
    }
    @ResponseBody
    @RequestMapping(value = "/ajaxgetCarlist")
    public List<Product> ajaxgetCarlist(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Product> cartlist = productService.selectAllshoppingCartInproduct(user);
        log.info("用户："+user.getUsername()+" 查询购物车内所有商品ajax");
        return cartlist;
    }

    @ResponseBody
    @RequestMapping(value = "/removeShoppingcartByID")
    public int removeShoppingcartByID(String shoppingcartid){
       ShoppingCart shoppingCart = new ShoppingCart();
       shoppingCart.setShoppingCartId(Integer.parseInt(shoppingcartid));
       return shoppingCartService.removeShoppingcartByID(shoppingCart);
    }

    @RequestMapping(value = "/clearshoppingcart/{productidandquantity}/{count}")
    public String clearshoppingcart(@PathVariable(value = "productidandquantity")String productidandquantity
                                    ,@PathVariable(value = "count")String count,HttpSession session){
        session.setAttribute("productidandquantity",productidandquantity);
        productidandquantitytrue = productidandquantity;
        /*System.out.println(productidandquantity);
        String[] productstrarr = productidandquantity.split("-");
        for(int i = 0 ;i < productstrarr.length;i++){
            System.out.println(productstrarr[i]);
        }*/
        Float count1 = Float.parseFloat(count);
        session.setAttribute("count",count1);
        return "redirect:/checkbuy";
    }
}

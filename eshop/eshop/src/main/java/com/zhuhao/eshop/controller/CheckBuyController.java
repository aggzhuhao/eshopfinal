package com.zhuhao.eshop.controller;

import ch.qos.logback.core.net.SyslogOutputStream;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.zhuhao.eshop.configure.AdminInterceptor;
import com.zhuhao.eshop.configure.AlipayConfig;
import com.zhuhao.eshop.entity.Order;
import com.zhuhao.eshop.entity.OrderDetail;
import com.zhuhao.eshop.entity.Product;
import com.zhuhao.eshop.entity.User;
import com.zhuhao.eshop.serviceImpl.OrderServiceImpl;
import com.zhuhao.eshop.serviceImpl.StockServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

@Controller
public class CheckBuyController {
    private static final Logger log = LoggerFactory.getLogger(CheckBuyController.class);

    @Autowired
    StockServiceImpl stockService;


    private Order order1;


    //单个结算
    @RequestMapping("/nowBuyproduct/{number}/{price}")
    public String getCount(@PathVariable(value = "number")int number,
                           @PathVariable(value="price") Float price,
                           HttpServletRequest request, HttpSession session
                           ){
        log.info("即将进入完善订单环节，商品数量为：" +number + ",商品价格为："+price+",总价格为 ：" + number*price);
        session.setAttribute("count",number*price);
        Product product1 = (Product) session.getAttribute("product");
        String productidandquantity = "productid:"+product1.getProductId()+",quantity:"+number+"-";
        session.setAttribute("productidandquantity",productidandquantity);
        session.setAttribute("quantity",number);
        return "redirect:/checkbuy";
    }

    @ResponseBody
    @RequestMapping("/jiesuanproductwechat")
    public String jiesuanproduct(String productIds,String quantitys,String payment
    ,String recevice,String province,String city,String county,String address,String zipcode,String telphone,HttpSession session){
        String productidandquantity = (String) session.getAttribute("productidandquantity");

        Order order = new Order();

        order.setCreateTime(new Date());
        Float count = (Float) session.getAttribute("count");
        order.setAmount(count);
        order.setParment(Integer.parseInt(payment));
        order.setPeceiver(recevice);
        order.setProvince(province);
        order.setCity(city);
        order.setCountry(county);
        order.setAddress(address);
        order.setZipcode(zipcode);
        order.setTelphone(telphone);
        User user = (User) session.getAttribute("user");
        order.setUsername(user.getUsername());
        order1 = order;
        /*Product product = new Product();
        Product product1 = (Product) session.getAttribute("product");
        product.setProductId(product1.getProductId());
        int quantity = (int) session.getAttribute("quantity");
        product.setCategoryId(quantity); //购买数量*/

        Integer i = stockService.jiesuanproduct(order,productidandquantity,user);
        return i.toString();
    }

    @RequestMapping("/alipay111")
    public void jiesuanproduct123(HttpServletRequest request, HttpServletResponse response){
        try {
            AlipayConfig.getorder(request,response,"123");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/alipay")
    public String jiesuanproduct321(HttpServletRequest request, HttpServletResponse response){

        return "alipay";
    }

    @RequestMapping("/jiesuanproduct/{payment}/{recevice}/{province}/{city}/{county}/{address}/{zipcode}/{telphone}")
    public void getorders(@PathVariable(value = "payment")String payment,
                          @PathVariable(value = "recevice")String recevice,
                          @PathVariable(value = "province")String province,
                          @PathVariable(value = "city")String city,
                          @PathVariable(value = "county")String county,
                          @PathVariable(value = "address")String address,@PathVariable(value = "zipcode")String zipcode,
                          @PathVariable(value = "telphone")String telphone
            ,HttpSession session,
                          HttpServletResponse response,HttpServletRequest request) throws IOException, AlipayApiException {
        String productidandquantity = (String) session.getAttribute("productidandquantity");
        Order order = new Order();

        order.setCreateTime(new Date());
        Float count = (Float) session.getAttribute("count");
        order.setAmount(count);
        order.setParment(Integer.parseInt(payment));
        order.setPeceiver(recevice);
        order.setProvince(province);
        order.setCity(city);
        order.setCountry(county);
        order.setAddress(address);
        order.setZipcode(zipcode);
        order.setTelphone(telphone);
        User user = (User) session.getAttribute("user");
        order.setUsername(user.getUsername());
        order1 = order;
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(UUID.randomUUID().toString().replaceAll("-", "").getBytes("ISO-8859-1"),"UTF-8");
        //付款金额，必填
        String total_amount = new String(count.toString().getBytes("ISO-8859-1"),"UTF-8");
        //订单名称，必填
        String subject = new String(UUID.randomUUID().toString().replaceAll("-", "").getBytes("ISO-8859-1"),"UTF-8");
        //商品描述，可空
        String body = "";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(result);


    }
    @RequestMapping("/insertorderandjiesuan")
    public String insertorderandjiesuan(HttpSession session){
        String productidandquantity = "";

            productidandquantity = ShopppingCartController.productidandquantitytrue;


        User user = (User) session.getAttribute("user");
        stockService.jiesuanproduct(order1,productidandquantity,user);
        return "orderlist";
    }
}

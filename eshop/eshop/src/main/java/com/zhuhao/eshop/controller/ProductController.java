package com.zhuhao.eshop.controller;

import com.zhuhao.eshop.entity.*;
import com.zhuhao.eshop.service.ProductService;
import com.zhuhao.eshop.service.TypeService;
import com.zhuhao.eshop.serviceImpl.CategoryImpl;
import com.zhuhao.eshop.serviceImpl.ProductServiceImpl;
import com.zhuhao.eshop.serviceImpl.StockServiceImpl;
import com.zhuhao.eshop.serviceImpl.TypeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring5.context.SpringContextUtils;
import org.thymeleaf.util.DateUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {
    public static final Logger log = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductServiceImpl productService;

    @Autowired
    StockServiceImpl stockService;

    @Autowired
    CategoryImpl category;

    @Autowired
    TypeServiceImpl typeService;

    //映射 主页
    @RequestMapping(value = {"/","/index"})
    public String index(HttpSession session, HttpServletRequest request){
        session.setAttribute("productlist", productService.selectProductLimit());
        session.setAttribute("categorylist",category.selectAllCategory());
        session.setAttribute("typelist",typeService.selectAllType());
        return "index";
        //return "index";打包需要
    }

    //查询所有数据记录 ajax
    @ResponseBody
    @RequestMapping(value = "/getAllProducts")
    public List<Product> getAllProducts(){
        return productService.selectAllProduct();
    }

    //根据ID查询产品信息
    @RequestMapping(value = "/getproductById/{productId}/{categoryId}")
    public String getproductById(@PathVariable(value = "productId",required = false) Integer productId,
                                 @PathVariable(value = "categoryId",required = false) Integer categoryId,HttpSession session){

        session.setAttribute("category",category.selectBycategoryId(categoryId));
        log.info("根据ID查询商品信息开始： 商品ID: "+productId + " ，品种ID: " +categoryId);
        Product product = new Product();
        product.setProductId(productId);
        //商品信息
        session.setAttribute("product",productService.selectProductById(product));
        //库存信息
        session.setAttribute("quantity",stockService.selectStockByPId(product).getQuantity());
        return "redirect:/detail";
    }

    //模糊搜索产品
    @ResponseBody
    @RequestMapping("/searchlikeproduct")
    public List<Product> searchlikeproduct(@RequestParam(value = "data",required = false)String data){
        String description = data.trim();
        System.out.println(productService.selectlikeProduct(description));
        return productService.selectlikeProduct(description);
    }

    //查询购物车内的商品详情和库存
    @RequestMapping("/selectAllshoppingCartInproduct")
    public String selectAllshoppingCartInproduct(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Product> cartlist = productService.selectAllshoppingCartInproduct(user);
        /*for(Product p : cartlist){
            System.out.println(p +">>>>库存：" +p.getStocknumber() +"  >>>>购物车内的数量" +p.getCartnumber()+"  >>>>购物车id " +p.getShoppingcartid());
        }*/
        log.info("用户："+user.getUsername()+" 查询购物车内所有商品");
        session.setAttribute("cartlist",cartlist);
        return "redirect:/cart";
    }

    @RequestMapping("/insertproduct")
    public String insertproduct(HttpSession session){
        List<Type> typelistinsert = typeService.selectAllTypeNobyCid();
        session.setAttribute("typelistinsert",typelistinsert);
        return "insertproduct";
    }

    @Transactional
    @RequestMapping("/insertproductaction")
    public String insertproductaction(Product product, Integer quantity, MultipartFile fileinp) throws Exception {
        if(!fileinp.isEmpty()){

            String filepath = loadpic(fileinp);

            if(product.getDicount() == null){
                product.setDicount(2);
            }
            if(product.getNovaltyStatus() == null){
                product.setNovaltyStatus(2);
            }


            product.setDate(new Date());
            product.setImgpath(filepath);
            productService.insertproduct(product);
            Stock stock = new Stock();
            stock.setQuantity((long)quantity);
            stock.setProductId(product.getProductId());
            stockService.insertstockp(stock);

        }
         return "redirect:/adminindex";
    }

    @ResponseBody
    @RequestMapping("/huixianpic")
    public String huixianpic(MultipartFile data) throws Exception {
        String picpath = loadpic(data);
        return picpath;
    }

    @Transactional
    @ResponseBody
    @RequestMapping("/deleteproductstock")
    public boolean deleteproductstock(String productid,HttpSession session){
        try{
            Product product = new Product();
            product.setProductId(Integer.parseInt(productid));
            Stock stock = (Stock) session.getAttribute("stock1");
            stockService.deletestock(stock);
            productService.deleteproduct(product);
        }catch (Exception e){
            return false;
        }

        return true;
    }

    @Transactional
    @RequestMapping("/updateproductstock/{productid}")
    public String updateproductstock(Product product,@PathVariable(value = "productid",required = true) Integer productid
            ,Integer quantity, MultipartFile fileinp,HttpSession session){
        try{


                String filepath = loadpic(fileinp);

                if(product.getDicount() == null){
                    product.setDicount(2);
                }
                if(product.getNovaltyStatus() == null){
                    product.setNovaltyStatus(2);
                }
                product.setDate(new Date());
                product.setProductId(productid);
                if(filepath != null){
                    product.setImgpath(filepath);
                }else{
                    Product p = (Product) session.getAttribute("product");
                    product.setImgpath(p.getImgpath());
                }

                productService.updateproduct(product);
                Stock stock = (Stock) session.getAttribute("stock1");
                stock.setQuantity((long)quantity);
                stock.setProductId(product.getProductId());
                stockService.updatestock(stock);

        }catch (Exception e){
            return "redirect:/adminindex";
        }

        return "redirect:/adminindex";
    }



    @ResponseBody
    @RequestMapping("/getnewProducts")
    public List<Product> getnewProducts(){
        return productService.selectnewproduct();
    }

    @ResponseBody
    @RequestMapping("/getdiscountProducts")
    public List<Product> getdiscountProducts(){
        return productService.selectdiscountproduct();
    }

    @RequestMapping("/changeproductaction/{productId}/{categoryId}")
    public String changeproductaction(@PathVariable(value = "productId",required = false) Integer productId,
                                 @PathVariable(value = "categoryId",required = false) Integer categoryId,HttpSession session){

        session.setAttribute("category",category.selectBycategoryId(categoryId));
        log.info("根据ID查询商品信息开始： 商品ID: "+productId + " ，品种ID: " +categoryId);
        Product product = new Product();
        product.setProductId(productId);
        //商品信息
        session.setAttribute("product",productService.selectProductById(product));
        //库存信息
        session.setAttribute("stock1",stockService.selectStockByPId(product));
        return "redirect:/changeproduct";
    }


    @RequestMapping("/changeproduct")
    public String changeproduct(){
        return "changeproduct";
    }


    //存图片
    private String loadpic(MultipartFile fileinp)  {
        if(!fileinp.isEmpty()){
        byte[] bytes = new byte[0];
        try {
            bytes = fileinp.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String originalFilename = fileinp.getOriginalFilename();
        int indexOf = originalFilename.indexOf(".");
        String extend = originalFilename.substring(indexOf);
        String strPath = "src/main/resources/static/images";

        File path = new File(strPath);
        if (!path.exists()) {
            path.mkdirs();
        }
        String filename = UUID.randomUUID().toString().replaceAll("-", "");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(strPath).append("/").append(filename).append(extend);
        File name = new File(stringBuilder.toString());
        try {
            FileCopyUtils.copy(bytes, name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filepath = "images/"+filename+extend;
        return filepath;
        }
        return null;
    }

    //推荐商品
    @ResponseBody
    @RequestMapping("/gettuijianproduct")
    public List<Product> gettuijianproduct(String data,String productid){
        Category category = new Category();
        category.setCategoryId(Integer.parseInt(data));
        List<Type> typelist = typeService.selecttypeidbycid(category);
        Integer typeid = typelist.get(0).getTypeId();
        Category category1 = new Category();
        category1.setTypeId(typeid);
        List<Product> products = productService.selecttuijianproduct(category1);
        int productsize = products.size();
        List<Product> productlist = new ArrayList<Product>(3);
        if(productsize > 3){
            for(int i = 0; i < 3;i++){
                int randomNumber = (int) (Math.random()*productsize);
                productlist.add(products.get(randomNumber));
            }
            return productlist;
        }else{
            return products;
        }




    }

}

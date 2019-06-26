package com.zhuhao.eshop.controller;

import com.zhuhao.eshop.entity.Order;
import com.zhuhao.eshop.entity.User;
import com.zhuhao.eshop.serviceImpl.OrderServiceImpl;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderlistController {
    @Autowired
    OrderServiceImpl orderService;

    @ResponseBody
    @RequestMapping("/getOrderlistAjax")
    public Map<Integer,List<Order>> getOrderlistAjax(String startnumber, String endnumber, HttpSession session) throws ParseException {
        User user = (User) session.getAttribute("user");
        Order order = new Order();
        order.setUsername(user.getUsername());
        order.setStartnumber(Integer.parseInt(startnumber));
        order.setEndnumber(Integer.parseInt(endnumber));
        int count = orderService.getOrderlistAjaxCount(order);
        List<Order> orderList = orderService.getOrderlistAjaxlimit(order);
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Order o : orderList){
            o.setCreatTimestr(s.format(o.getCreateTime()));
        }
        Map<Integer,List<Order>> orderMap = new HashMap<Integer,List<Order>>();
        orderMap.put(count,orderList);
        return orderMap;
    }

    @RequestMapping("/exportexcel")
    public void exportexcel(HttpServletRequest request, HttpServletResponse response) {
        String[] title = {"订单编号","订单日期","金额(单位￥)","收货人"};
        String filename = "用户订单表"+System.currentTimeMillis()+".xls";

        User user = (User) request.getSession().getAttribute("user");
        List<Order> order = orderService.selectorders(user);
        HSSFWorkbook wb = this.getworkbook(title,order);
        try {
            this.setResponseHeader(response, filename);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HSSFWorkbook getworkbook(String[] title,List<Order> orderlist) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("订单列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = wb.createCellStyle();
        HSSFCell cell = null;
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }
        String[][] content;
        content = new String [orderlist.size()][];
        for (int i = 0; i < orderlist.size(); i++) {
            content[i] = new String[title.length];
            Order order1 = orderlist.get(i);
            content[i][0] = String.valueOf(order1.getOrderId());
            content[i][1] = String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order1.getCreateTime()));
            content[i][2] = String.valueOf(order1.getAmount());
            content[i][3] = order1.getPeceiver();
        }
        for(int i=0;i<orderlist.size();i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<content[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(content[i][j]);
            }
        }
        return wb;

    }
    //设置编码
    private void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            fileName = new String(fileName.getBytes(),"ISO8859-1");

            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

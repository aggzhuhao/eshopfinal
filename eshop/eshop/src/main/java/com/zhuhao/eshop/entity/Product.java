package com.zhuhao.eshop.entity;

import java.util.Date;

public class Product {
    private Integer productId;

    private String description;

    private Float price;

    private Integer dicount;

    private String feature;

    private String imgpath;

    private Integer novaltyStatus;

    private Integer categoryId;

    private Date date;

    private Integer cartnumber;

    private Integer stocknumber;

    private Integer shoppingcartid;

    public Product() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getDicount() {
        return dicount;
    }

    public void setDicount(Integer dicount) {
        this.dicount = dicount;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath == null ? null : imgpath.trim();
    }

    public Integer getNovaltyStatus() {
        return novaltyStatus;
    }

    public void setNovaltyStatus(Integer novaltyStatus) {
        this.novaltyStatus = novaltyStatus;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", dicount=" + dicount +
                ", feature='" + feature + '\'' +
                ", imgpath='" + imgpath + '\'' +
                ", novaltyStatus=" + novaltyStatus +
                ", categoryId=" + categoryId +
                ", date=" + date +
                '}';
    }

    public Integer getCartnumber() {
        return cartnumber;
    }

    public void setCartnumber(Integer cartnumber) {
        this.cartnumber = cartnumber;
    }

    public Integer getStocknumber() {
        return stocknumber;
    }

    public void setStocknumber(Integer stocknumber) {
        this.stocknumber = stocknumber;
    }

    public Integer getShoppingcartid() {
        return shoppingcartid;
    }

    public void setShoppingcartid(Integer shoppingcartid) {
        this.shoppingcartid = shoppingcartid;
    }
}
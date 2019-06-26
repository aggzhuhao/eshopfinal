package com.zhuhao.eshop.entity;

import java.util.Date;

public class Order {
    private Integer orderId;

    private String creatTimestr;

    private Date createTime;

    private Float amount;

    private Integer parment;

    private String peceiver;

    private String province;

    private String city;

    private String country;

    private String address;

    private String zipcode;

    private String telphone;

    private String username;

    private Integer startnumber;

    private Integer endnumber;

    public Order() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getParment() {
        return parment;
    }

    public void setParment(Integer parment) {
        this.parment = parment;
    }

    public String getPeceiver() {
        return peceiver;
    }

    public void setPeceiver(String peceiver) {
        this.peceiver = peceiver == null ? null : peceiver.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getEndnumber() {
        return endnumber;
    }

    public void setEndnumber(Integer endnumber) {
        this.endnumber = endnumber;
    }

    public Integer getStartnumber() {
        return startnumber;
    }

    public void setStartnumber(Integer startnumber) {
        this.startnumber = startnumber;
    }

    public String getCreatTimestr() {
        return creatTimestr;
    }

    public void setCreatTimestr(String creatTimestr) {
        this.creatTimestr = creatTimestr;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", creatTimestr='" + creatTimestr + '\'' +
                ", createTime=" + createTime +
                ", amount=" + amount +
                ", parment=" + parment +
                ", peceiver='" + peceiver + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", telphone='" + telphone + '\'' +
                ", username='" + username + '\'' +
                ", startnumber=" + startnumber +
                ", endnumber=" + endnumber +
                '}';
    }
}
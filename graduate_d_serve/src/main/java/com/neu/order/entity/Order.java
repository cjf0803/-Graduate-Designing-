package com.neu.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;

/**
 * @Author cjf
 * @Date 2022/9/19
 **/
@ApiModel("订单的实体类")
public class Order {
    @ApiModelProperty("订单的id")
    private String oid;
    @ApiModelProperty("订单用户")
    private String username;
    @ApiModelProperty("订单总价")
    private double totalsum;
    @ApiModelProperty("订单预期收入")
    private double exincome;
    @ApiModelProperty("下单时间")
    private Date time;
    @ApiModelProperty("支付方式")
    private Pay payWay=new Pay();
    @ApiModelProperty("订单状态")
    private State state=new State();
    @ApiModelProperty("订单执行时间")
    private Date  executetime;
    @ApiModelProperty("订单执行者")
    private String author;

    public Order(String oid, String username, double totalsum, double exincome, Date time, Pay payWay, State state, Date executetime, String author) {
        this.oid = oid;
        this.username = username;
        this.totalsum = totalsum;
        this.exincome = exincome;
        this.time = time;
        this.payWay = payWay;
        this.state = state;
        this.executetime = executetime;
        this.author = author;
    }

    public Order(String oid, String author) {
        this.oid = oid;
        this.author = author;
    }

    public Order() {
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTotalsum() {
        return totalsum;
    }

    public void setTotalsum(double totalsum) {
        this.totalsum = totalsum;
    }

    public double getExincome() {
        return exincome;
    }

    public void setExincome(double exincome) {
        this.exincome = exincome;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Pay getPayWay() {
        return payWay;
    }

    public void setPayWay(Pay payWay) {
        this.payWay = payWay;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getExecutetime() {
        return executetime;
    }

    public void setExecutetime(Date executetime) {
        this.executetime = executetime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

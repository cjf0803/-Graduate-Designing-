package com.neu.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cjf
 * @Date 2022/9/7
 **/
@ApiModel("用户资产的实体类")
public class Money {
    @ApiModelProperty("用户的资产id")
    private String mid;
    @ApiModelProperty("用户的原有总资产")
    private double totalmoney;
    @ApiModelProperty("用户的收入")
    private double income;
    @ApiModelProperty("用户的支出")
    private double pay;
    @ApiModelProperty("用户的余额")
    private double balance;

    public Money(String mid, double totalmoney, double income, double pay, double balance) {
        this.mid = mid;
        this.totalmoney = totalmoney;
        this.income = income;
        this.pay = pay;
        this.balance = balance;
    }

    public Money() {
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

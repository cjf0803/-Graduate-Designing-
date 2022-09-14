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
    private String  totalmoney;
    @ApiModelProperty("用户的收入")
    private String income;
    @ApiModelProperty("用户的支出")
    private String pay;
    @ApiModelProperty("用户的余额")
    private String balance;

    public Money(String mid, String totalmoney, String income, String pay, String balance) {
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

    public String getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(String totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}

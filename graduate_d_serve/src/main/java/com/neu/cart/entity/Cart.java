package com.neu.cart.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cjf
 * @Date 2022/9/18
 **/
@ApiModel("预购的实体类")
public class Cart {
    @ApiModelProperty("预购id")
    private String cid;
    @ApiModelProperty("预购用户名")
    private String username;
    @ApiModelProperty("预购产品图")
    private String pimg;
    @ApiModelProperty("预购产品名称")
    private String pname;
    @ApiModelProperty("预购产品单份价格")
    private String price;
    @ApiModelProperty("预购产品平均收益率")
    private String averagerate;
    @ApiModelProperty("预购数量")
    private int pnum;
    @ApiModelProperty("预购小计")
    private double sum;

    public Cart() {
    }

    public Cart(String cid, int pnum) {
        this.cid = cid;
        this.pnum = pnum;
    }

    public Cart(String cid, double sum) {
        this.cid = cid;
        this.sum = sum;
    }

    public Cart(String pname, int pnum, double sum) {
        this.pname = pname;
        this.pnum = pnum;
        this.sum = sum;
    }

    public Cart(String cid, String username, String pimg, String pname, String price, String averagerate, int pnum, double sum) {
        this.cid = cid;
        this.username = username;
        this.pimg = pimg;
        this.pname = pname;
        this.price = price;
        this.averagerate = averagerate;
        this.pnum = pnum;
        this.sum = sum;
    }

    public Cart(String username, String pimg, String pname, String price, String averagerate, int pnum, double sum) {
        this.username = username;
        this.pimg = pimg;
        this.pname = pname;
        this.price = price;
        this.averagerate = averagerate;
        this.pnum = pnum;
        this.sum = sum;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAveragerate() {
        return averagerate;
    }

    public void setAveragerate(String averagerate) {
        this.averagerate = averagerate;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}

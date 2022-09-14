package com.neu.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;

/**
 * @Author cjf
 * @Date 2022/9/14
 **/
@ApiModel("产品的实体类")
public class Product {
    @ApiModelProperty("产品的id")
    private String id;
    @ApiModelProperty("产品的名称")
    private String pname;
    @ApiModelProperty("产品的图片")
    private String pimg;
    @ApiModelProperty("产品的类型")
    private Type ptype=new Type();
    @ApiModelProperty("产品的单份价格")
    private String price;
    @ApiModelProperty("产品的状态")
    private State pstate=new State();
    @ApiModelProperty("产品的总份额")
    private String totalnum;
    @ApiModelProperty("产品的平均收益率")
    private String averagerate;
    @ApiModelProperty("产品的最近收益率")
    private RecentlyRate recentlyRate=new RecentlyRate();
    @ApiModelProperty("产品的总规模")
    private String psize;
    @ApiModelProperty("产品的上市时间")
    private Date ptime;
    @ApiModelProperty("产品的经理")
    private Manager pmanager =new Manager();
    @ApiModelProperty("产品的描述")
    private String pdesc;

    public Product() {
    }

    public Product(String id, State pstate) {
        this.id = id;
        this.pstate = pstate;
    }

    public Product(String pname, Type ptype, String price, String totalnum, String averagerate, String psize, Manager pmanager, String pdesc) {
        this.pname = pname;
        this.ptype = ptype;
        this.price = price;
        this.totalnum = totalnum;
        this.averagerate = averagerate;
        this.psize = psize;
        this.pmanager = pmanager;
        this.pdesc = pdesc;
    }

    public Product(String id, String pname, String pimg, Type ptype, String price, String totalnum, String averagerate, String psize, Manager pmanager, String pdesc) {
        this.id = id;
        this.pname = pname;
        this.pimg = pimg;
        this.ptype = ptype;
        this.price = price;
        this.totalnum = totalnum;
        this.averagerate = averagerate;
        this.psize = psize;
        this.pmanager = pmanager;
        this.pdesc = pdesc;
    }

    public Product(String id, String pname, String pimg, Type ptype, String price, State pstate, String totalnum, String averagerate, RecentlyRate recentlyRate, String psize, Date ptime, Manager pmanager, String pdesc) {
        this.id = id;
        this.pname = pname;
        this.pimg = pimg;
        this.ptype = ptype;
        this.price = price;
        this.pstate = pstate;
        this.totalnum = totalnum;
        this.averagerate = averagerate;
        this.recentlyRate = recentlyRate;
        this.psize = psize;
        this.ptime = ptime;
        this.pmanager = pmanager;
        this.pdesc = pdesc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPimg() {
        return pimg;
    }

    public void setPimg(String pimg) {
        this.pimg = pimg;
    }

    public Type getPtype() {
        return ptype;
    }

    public void setPtype(Type ptype) {
        this.ptype = ptype;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public State getPstate() {
        return pstate;
    }

    public void setPstate(State pstate) {
        this.pstate = pstate;
    }

    public String getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(String totalnum) {
        this.totalnum = totalnum;
    }

    public String getAveragerate() {
        return averagerate;
    }

    public void setAveragerate(String averagerate) {
        this.averagerate = averagerate;
    }

    public RecentlyRate getRecentlyRate() {
        return recentlyRate;
    }

    public void setRecentlyRate(RecentlyRate recentlyRate) {
        this.recentlyRate = recentlyRate;
    }

    public String getPsize() {
        return psize;
    }

    public void setPsize(String psize) {
        this.psize = psize;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public Manager getPmanager() {
        return pmanager;
    }

    public void setPmanager(Manager pmanager) {
        this.pmanager = pmanager;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }
}

package com.neu.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cjf
 * @Date 2022/10/2
 **/
@ApiModel("订单详情的实体类")
public class Detail {
    @ApiModelProperty("订单详情的id（不是主键可重复）")
    private String id;
    @ApiModelProperty("订单详情的产品名称")
    private String pname;
    @ApiModelProperty("订单详情的产品图片")
    private String pimg;
    @ApiModelProperty("订单详情的产品价格")
    private String price;
    @ApiModelProperty("订单详情的产品平均收益率")
    private String averagerate;
    @ApiModelProperty("订单详情的下单者")
    private String username;

    public Detail(String id, String pname, String pimg, String price, String averagerate, String username) {
        this.id = id;
        this.pname = pname;
        this.pimg = pimg;
        this.price = price;
        this.averagerate = averagerate;
        this.username = username;
    }

    public Detail() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

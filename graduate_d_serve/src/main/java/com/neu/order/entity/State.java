package com.neu.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cjf
 * @Date 2022/9/19
 **/
@ApiModel("订单状态的实体类")
public class State {
    @ApiModelProperty("订单状态id")
    private  String sid;
    @ApiModelProperty("订单状态名称")
    private String sname;
    @ApiModelProperty("订单状态描述")
    private String pdesc;

    public State(String sid, String sname, String pdesc) {
        this.sid = sid;
        this.sname = sname;
        this.pdesc = pdesc;
    }

    public State() {
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }
}

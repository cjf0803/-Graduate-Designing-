package com.neu.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cjf
 * @Date 2022/9/14
 **/
@ApiModel("产品状态的实体类")
public class State {
    @ApiModelProperty("产品状态的id")
    private int sid;
    @ApiModelProperty("产品状态的名称")
    private String sname;
    @ApiModelProperty("产品状态的描述")
    private String sdesc;

    public State() {
    }

    public State(int sid, String sname, String sdesc) {
        this.sid = sid;
        this.sname = sname;
        this.sdesc = sdesc;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSdesc() {
        return sdesc;
    }

    public void setSdesc(String sdesc) {
        this.sdesc = sdesc;
    }
}

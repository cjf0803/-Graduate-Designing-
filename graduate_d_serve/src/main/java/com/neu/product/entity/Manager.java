package com.neu.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cjf
 * @Date 2022/9/14
 **/
@ApiModel("产品所属经理的实体类")
public class Manager {
    @ApiModelProperty("产品经理id")
    private String mid;
    @ApiModelProperty("产品经理姓名")
    private String mname;
    @ApiModelProperty("产品经理的描述")
    private String mdesc;

    public Manager() {
    }

    public Manager(String mid, String mname, String mdesc) {
        this.mid = mid;
        this.mname = mname;
        this.mdesc = mdesc;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMdesc() {
        return mdesc;
    }

    public void setMdesc(String mdesc) {
        this.mdesc = mdesc;
    }
}

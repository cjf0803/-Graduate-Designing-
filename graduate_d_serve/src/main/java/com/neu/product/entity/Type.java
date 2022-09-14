package com.neu.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cjf
 * @Date 2022/9/14
 **/
@ApiModel("产品类型的实体类")
public class Type {
    @ApiModelProperty("产品类型的id")
    private String tid;
    @ApiModelProperty("产品类型的名称")
    private String tname;
    @ApiModelProperty("产品类型的描述")
    private String tdesc;

    public Type() {
    }

    public Type(String tid, String tname, String tdesc) {
        this.tid = tid;
        this.tname = tname;
        this.tdesc = tdesc;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTdesc() {
        return tdesc;
    }

    public void setTdesc(String tdesc) {
        this.tdesc = tdesc;
    }
}

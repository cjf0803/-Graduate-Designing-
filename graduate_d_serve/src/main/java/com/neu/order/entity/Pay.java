package com.neu.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cjf
 * @Date 2022/9/19
 **/
@ApiModel("订单支付方式的实体类")
public class Pay {
    @ApiModelProperty("支付方式id")
    private String pid;
    @ApiModelProperty("支付方式名称")
    private String pname;
    @ApiModelProperty("支付方式描述")
    private String pdesc;

    public Pay(String pid, String pname, String pdesc) {
        this.pid = pid;
        this.pname = pname;
        this.pdesc = pdesc;
    }

    public Pay() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }
}

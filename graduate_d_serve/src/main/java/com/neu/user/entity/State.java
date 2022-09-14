package com.neu.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cjf
 * @Date 2022/9/6
 **/
@ApiModel("用户状态的实体类")
public class State {
    @ApiModelProperty("用户状态的id")
    private int sid;
    @ApiModelProperty("用户状态的名称")
    private String sname;
    @ApiModelProperty("用户状态的描述")
    private String sdesc;

    public State(int sid, String sname, String sdesc) {
        this.sid = sid;
        this.sname = sname;
        this.sdesc = sdesc;
    }

    public State() {
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

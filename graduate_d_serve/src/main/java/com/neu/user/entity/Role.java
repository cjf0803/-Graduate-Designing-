package com.neu.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;

/**
 * @Author cjf
 * @Date 2022/9/6
 **/
@ApiModel("角色的实体类")
public class Role {
    @ApiModelProperty("角色的id")
    private String rid;
    @ApiModelProperty("角色的名称")
    private String rname;
    @ApiModelProperty("角色的描述")
    private  String rdesc;
    @ApiModelProperty("角色被操作的日期")
    private Date rdate;
    @ApiModelProperty("角色执行者")
    private String rauthor;

    public Role(String rid, String rname, String rdesc, Date rdate, String rauthor) {
        this.rid = rid;
        this.rname = rname;
        this.rdesc = rdesc;
        this.rdate = rdate;
        this.rauthor = rauthor;
    }

    public Role(String rid, String rname, String rdesc, String rauthor) {
        this.rid = rid;
        this.rname = rname;
        this.rdesc = rdesc;
        this.rauthor = rauthor;
    }

    public Role(String rname, String rdesc, String rauthor) {
        this.rname = rname;
        this.rdesc = rdesc;
        this.rauthor = rauthor;
    }

    public Role() {
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    public String getRauthor() {
        return rauthor;
    }

    public void setRauthor(String rauthor) {
        this.rauthor = rauthor;
    }
}

package com.neu.product.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cjf
 * @Date 2022/9/14
 **/
@ApiModel("产品最近收益率的实体类")
public class RecentlyRate {
    @ApiModelProperty("产品最近收益率的id")
    private String rid;
    @ApiModelProperty("产品一月收益率")
    private String jan;
    @ApiModelProperty("产品三月收益率")
    private String mar;
    @ApiModelProperty("产品五月收益率")
    private String may;
    @ApiModelProperty("产品七月收益率")
    private String july;
    @ApiModelProperty("产品九月收益率")
    private String sep;
    @ApiModelProperty("产品十一月收益率")
    private String nov;

    public RecentlyRate() {
    }

    public RecentlyRate(String rid, String jan, String mar, String may, String july, String sep, String nov) {
        this.rid = rid;
        this.jan = jan;
        this.mar = mar;
        this.may = may;
        this.july = july;
        this.sep = sep;
        this.nov = nov;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getJan() {
        return jan;
    }

    public void setJan(String jan) {
        this.jan = jan;
    }

    public String getMar() {
        return mar;
    }

    public void setMar(String mar) {
        this.mar = mar;
    }

    public String getMay() {
        return may;
    }

    public void setMay(String may) {
        this.may = may;
    }

    public String getJuly() {
        return july;
    }

    public void setJuly(String july) {
        this.july = july;
    }

    public String getSep() {
        return sep;
    }

    public void setSep(String sep) {
        this.sep = sep;
    }

    public String getNov() {
        return nov;
    }

    public void setNov(String nov) {
        this.nov = nov;
    }
}

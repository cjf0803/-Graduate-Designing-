package com.neu.depart.entity;

public class Depart {
    private String departid;
    private String depart_name;

    public Depart(String departid, String depart_name) {
        this.departid = departid;
        this.depart_name = depart_name;
    }

    public Depart() {
    }

    public String getDepartid() {
        return departid;
    }

    public void setDepartid(String departid) {
        this.departid = departid;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }
}

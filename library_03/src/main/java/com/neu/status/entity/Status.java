package com.neu.status.entity;

/**
 * @author 陈锦房
 */
public class Status {
    private int status_id;
    private String status_name;

    public Status(int status_id, String status_name) {
        this.status_id = status_id;
        this.status_name = status_name;
    }

    public Status() {
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}

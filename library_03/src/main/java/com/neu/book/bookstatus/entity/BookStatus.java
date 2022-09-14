package com.neu.book.bookstatus.entity;

/**
 * @author 陈锦房
 */
public class BookStatus {
    private String status_id;
    private String status_name;

    public BookStatus(String status_id, String status_name) {
        this.status_id = status_id;
        this.status_name = status_name;
    }

    public BookStatus() {
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}

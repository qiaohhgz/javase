package com.jim.demo.annotation;

/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 12/12/13
 * Time: 10:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class Book {
    private String status;

    public String getStatus() {
        return status;
    }

    @Undeletable(status = "可选")
    public void setStatus(String status) {
        this.status = status;
    }
}

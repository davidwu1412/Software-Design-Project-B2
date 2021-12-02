package com.example.fileioexample;

public class OrderObj {

    //should this be int?
    private String orderId;

    public OrderObj(){

    }


    public OrderObj(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

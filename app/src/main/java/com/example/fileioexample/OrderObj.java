package com.example.fileioexample;

public class OrderObj {

    //should this be int?
    private String orderId;
    private String fulfilled;
    private String customerUsername;

    public OrderObj(){

    }

    public String getFulfilled() {
        return this.fulfilled;
    }

    public void setFulfilled(String fulfilled) {
        this.fulfilled = fulfilled;
    }

    public String getCustomerUsername() {
        return this.customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public OrderObj(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

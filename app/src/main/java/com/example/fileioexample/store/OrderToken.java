package com.example.fileioexample.store;

public class OrderToken {

    private String owner;
    private int orderId;

    public OrderToken() {}

    public OrderToken(String owner, int orderId) {
        this.owner = owner;
        this.orderId = orderId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}

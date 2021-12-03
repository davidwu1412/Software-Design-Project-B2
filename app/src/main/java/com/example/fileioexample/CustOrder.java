package com.example.fileioexample;

import java.util.ArrayList;
import java.util.Objects;

public class CustOrder {

    private ArrayList<CustProduct> products;
    private double totalCost;
    private String fulfilled;
    private String orderId;
    private String placed;

    public String getOrderId() {
        return orderId;
    }

    public String getPlaced() {
        return placed;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    private String customerUsername;


    public ArrayList<CustProduct> getProducts() {
        return products;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getFulfilled() {
        return fulfilled;
    }


    @Override
    public String toString() {
        return "Order: "+ orderId+"\n"+"TotalCost: "+ totalCost + "\n" +"Placed: "+ placed;
    }

}
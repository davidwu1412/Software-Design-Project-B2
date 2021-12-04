package com.example.fileioexample.store;

import java.util.ArrayList;
import java.util.Objects;

public class Order {

    private ArrayList<Product> products;
    private double totalCost;
    private boolean fulfilled;
    private String customerUsername;
    private int orderId;

    //Optional fields (these may not be used in the final product)
    private String datePlaced;
    private String timePlaced;
    private String timeCompleted;

    public Order() {
        products = new ArrayList<Product>();
    }

    public Order(ArrayList<Product> products, double totalCost, boolean fulfilled, String customerUsername, int orderId) {
        this.products = products;
        this.totalCost = totalCost;
        this.fulfilled = fulfilled;
        this.customerUsername = customerUsername;
        this.orderId = orderId;
    }

    public void updateTotalCost() {

        double newTotalCost = 0;

        for(Product p: getProducts()){
            newTotalCost += p.getPrice() * p.getQuantity();
        }

        newTotalCost = (double) (Math.round(newTotalCost * 100)) / 100; //Round to 2 decimal places

        setTotalCost(newTotalCost);

    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public double getTotalCost() {
        return totalCost;
    }

    //We don't want the total cost to be set outside of the updateTotalCost function
    private void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(String datePlaced) {
        this.datePlaced = datePlaced;
    }

    public String getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(String timePlaced) {
        this.timePlaced = timePlaced;
    }

    public String getTimeCompleted() {
        return timeCompleted;
    }

    public void setTimeCompleted(String timeCompleted) {
        this.timeCompleted = timeCompleted;
    }

    //Note: we currently don't use any of the optional fields when checking equality
    //and when generating hash codes
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.totalCost, totalCost) == 0 && fulfilled == order.fulfilled && orderId == order.orderId && products.equals(order.products) && customerUsername.equals(order.customerUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, totalCost, fulfilled, customerUsername, orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "products=" + products +
                ", totalCost=" + totalCost +
                ", fulfilled=" + fulfilled +
                ", storeName='" + customerUsername + '\'' +
                ", orderNumber=" + orderId +
                ", datePlaced='" + datePlaced + '\'' +
                ", timePlaced='" + timePlaced + '\'' +
                ", timeCompleted='" + timeCompleted + '\'' +
                '}';
    }

}
package com.example.fileioexample.store;

import java.util.ArrayList;
import java.util.Objects;

public class Store {

    private String storeName;
    private String storeAddress;
    private ArrayList<Product> availableProducts;
    private ArrayList<Order> orders;

    public Store() {
        this.availableProducts = new ArrayList<Product>();
        this.orders = new ArrayList<Order>();
    }

    public Store(String name, String address) {
        this.storeName = name;
        this.storeAddress = address;
        this.availableProducts = new ArrayList<Product>();
        this.orders = new ArrayList<Order>();
    }

    public Store(String name, String address, ArrayList<Product> availableProducts, ArrayList<Order> orders) {
        this.storeName = name;
        this.storeAddress = address;
        this.availableProducts = availableProducts;
        this.orders = orders;
    }

    public String getName() {
        return storeName;
    }

    public void setName(String name) {
        this.storeName = name;
    }

    public String getAddress() {
        return storeAddress;
    }

    public void setAddress(String address) {
        this.storeAddress = address;
    }

    public ArrayList<Product> getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(ArrayList<Product> availableProducts) {
        this.availableProducts = availableProducts;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return storeName.equals(store.storeName) && storeAddress.equals(store.storeAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeName, storeAddress);
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + storeName + '\'' +
                ", address='" + storeAddress + '\'' +
                '}';
    }

}

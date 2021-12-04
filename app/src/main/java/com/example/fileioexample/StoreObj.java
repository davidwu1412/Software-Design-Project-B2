package com.example.fileioexample;


// This class is to be used for ListStores actiivity
public class StoreObj {
    private String storeName;
    private String storeAddress;

    public StoreObj() {
    }

    public StoreObj(String storeName, String storeAddress) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}

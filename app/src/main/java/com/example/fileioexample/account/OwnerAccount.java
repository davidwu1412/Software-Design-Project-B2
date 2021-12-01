package com.example.fileioexample.account;

import com.example.fileioexample.store.Store;

import java.util.Objects;

public class OwnerAccount extends Account {

    private String storeName;
    private String storeAddress;

    public OwnerAccount(){

    }

    public OwnerAccount(String username, String storeName, String storeAddress) {
        super(username);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OwnerAccount that = (OwnerAccount) o;
        return storeName.equals(that.storeName) && storeAddress.equals(that.storeAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), storeName, storeAddress);
    }

    @Override
    public String toString() {
        return "OwnerAccount{" +
                "storeName='" + storeName + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                '}';
    }

}

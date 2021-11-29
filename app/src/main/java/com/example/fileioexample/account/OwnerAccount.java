package com.example.fileioexample.account;

import com.example.fileioexample.store.Store;

public class OwnerAccount extends Account {

    private Store store;

    public OwnerAccount(){

    }

    public OwnerAccount(String username, String storeName, String storeAddress) {
        super(username);
        this.store = new Store(storeName, storeAddress);
    }

    public OwnerAccount(String username, Store store) {
        super(username);
        this.store = store;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "OwnerAccount{" +
                "store=" + store +
                '}';
    }
}

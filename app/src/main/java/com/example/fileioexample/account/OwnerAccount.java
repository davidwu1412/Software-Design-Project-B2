package com.example.fileioexample.account;

import com.example.fileioexample.store.Store;

public class OwnerAccount extends Account {

    private Store store;

    public OwnerAccount(String username, String password, String storeName, String storeAddress) {
        super(username, password);
        this.store = new Store(storeName, storeAddress);
    }

    public OwnerAccount(String username, String password, Store store) {
        super(username, password);
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

package com.example.fileioexample.utils;

import com.example.fileioexample.account.CustomerAccount;
import com.example.fileioexample.store.Order;
import com.example.fileioexample.store.OrderToken;
import com.example.fileioexample.store.Store;

public class CurrentUser {

    public static String username;
    public static CustomerAccount customer;
    public static Store store;
    public static OrderToken currentOrderToken = new OrderToken();

}

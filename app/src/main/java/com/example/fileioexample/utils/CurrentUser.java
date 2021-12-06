package com.example.fileioexample.utils;

import com.example.fileioexample.account.CustomerAccount;
import com.example.fileioexample.store.OrderToken;
import com.example.fileioexample.store.Product;
import com.example.fileioexample.store.Store;

import java.util.ArrayList;

public class CurrentUser {

    public static String customerUsername;
    public static String ownerUsername;
    public static CustomerAccount customer;
    public static Store store;
    public static OrderToken currentOrderToken = new OrderToken();
    public static ArrayList<Product> cart = new ArrayList<Product>();

}

package com.example.fileioexample;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Customer extends Account implements FirebaseIO{
    private String username;

    private Customer(String n){
        username = n;
    }

    @Override
    public void add(Product p){


    }
    @Override
    public void remove(Product p){


    }

    public void place(){

    }

    public static Customer create(String n, String p) throws Exception {
        Customer c = new Customer(Account.create(n,p,"Customer Acc"));
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Customer Acc").child("C1").child("Pending");
        ref.setValue("1233");

        return c;

    }

    public static Customer login(String n, String p) throws Exception{
        Customer c = new Customer(Account.login(n,p,"Customer Acc"));
        return c;
    }

}

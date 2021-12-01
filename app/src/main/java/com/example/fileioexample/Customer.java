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
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Customer Acc").child(n).child("Pending");
        ref1.setValue("000");
        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Customer Acc").child(n).child("Completed");
        ref2.setValue("000");
        DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference("Customer Acc").child(n).child("Cart");
        ref3.setValue("000");

        return c;

    }

    public static Customer login(String n, String p) throws Exception{
        Customer c = new Customer(Account.login(n,p,"Customer Acc"));
        return c;
    }

}

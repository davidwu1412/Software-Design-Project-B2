package com.example.fileioexample;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Owner extends Account implements FirebaseIO{
    private String username;

    private Owner(String n){
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

    public static Owner create(String n, String p) throws Exception {
        Owner o  = new Owner(Account.create(n,p,"Customer Acc"));
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Customer Acc").child("C1").child("Pending");
        ref.setValue("null");

        return o;

    }

    public static Owner login(String n, String p) throws Exception{
        Owner o = new Owner(Account.login(n,p,"Customer Acc"));
        return o;
    }

}

package com.example.fileioexample.utils;

import android.util.Log;

import com.example.fileioexample.account.CustomerAccount;
import com.example.fileioexample.account.OwnerAccount;
import com.example.fileioexample.login.LoginModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DatabaseUtils {

    public static final DatabaseReference ACCOUNTS_REF = FirebaseDatabase.getInstance().getReference("accounts");
    public static final DatabaseReference CUSTOMER_ACCOUNTS_REF = ACCOUNTS_REF.child("customers");
    public static final DatabaseReference OWNER_ACCOUNTS_REF = ACCOUNTS_REF.child("owners");
    public static final DatabaseReference PASSWORDS_REF = ACCOUNTS_REF.child("passwords");

    //This method checks if a username is taken by an existing account in the database
    //It returns true if the username is valid (not taken) and false if it is taken
    public static boolean validateUsername(String username){

        //Check the username is being used by a customer
        if(LoginModel.customers != null && LoginModel.customers.containsKey(username)){
            return false;
        }

        //Check the username is being used by an owner
        if(LoginModel.owners != null && LoginModel.owners.containsKey(username)){
            return false;
        }

        //If the username is not in use, return true
        return true;

    }

    //This method checks if a store address is taken by an existing account in the database
    //It returns true if the address is valid (not taken) and false if it is taken
    public static boolean validateNewStoreAddress(String newStoreName, String newStoreAddress){

        //If there are no owners, return true
        if(LoginModel.owners == null)
            return true;

        for (Map.Entry entry : LoginModel.owners.entrySet()) {

            //Get the address from each owner
            HashMap owner = (HashMap) entry.getValue();
            String storeName = (String) owner.get("storeName");
            String address = (String) owner.get("storeAddress");

            //If the address matches newStoreAddress, the address is taken, so return false
            if(newStoreName.equals(storeName) && newStoreAddress.equals(address)){
                Log.i("demo", "store name and address taken"); //Test
                return false;
            }
        }

        //Return true if the address is not found
        return true;

    }

    //Write customer account to the database
    public static void addCustomerAccount(String username, String password){

        DatabaseUtils.CUSTOMER_ACCOUNTS_REF.child(username).setValue(new CustomerAccount(username));
        DatabaseUtils.PASSWORDS_REF.child(username).setValue(password);

    }

    //Write an owner account to the database
    public static void addOwnerAccount(String username, String storeName, String storeAddress, String password){

        DatabaseUtils.OWNER_ACCOUNTS_REF.child(username).setValue(new OwnerAccount(username, storeName, storeAddress));
        DatabaseUtils.PASSWORDS_REF.child(username).setValue(password);

    }



}

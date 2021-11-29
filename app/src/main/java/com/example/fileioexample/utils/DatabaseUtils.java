package com.example.fileioexample.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.fileioexample.account.OwnerAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
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
        if(DatabaseManager.customers != null && DatabaseManager.customers.containsKey(username)){
            return false;
        }

        //Check the username is being used by an owner
        if(DatabaseManager.owners != null && DatabaseManager.owners.containsKey(username)){
            return false;
        }

        //If the username is not in use, return true
        return true;

    }

    //This method checks if a store address is taken by an existing account in the database
    //It returns true if the address is valid (not taken) and false if it is taken
    public static boolean validateNewStoreAddress(String newStoreName, String newStoreAddress){

        //If there are no owners, return true
        if(DatabaseManager.owners == null)
            return true;

        for (Map.Entry entry : DatabaseManager.owners.entrySet()) {

            //Get the address from each owner
            HashMap owner = (HashMap) entry.getValue();
            HashMap store = (HashMap) owner.get("store");
            String storeName = (String) store.get("name");
            String address = (String) store.get("address");

            //If the address matches newStoreAddress, the address is taken, so return false
            if(newStoreName.equals(storeName) && newStoreAddress.equals(address)){
                Log.i("demo", "store name and address taken"); //Test
                return false;
            }
        }

        //Return true if the address is not found
        return true;

    }

}

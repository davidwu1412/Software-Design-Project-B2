package com.example.fileioexample.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DatabaseManager {

    public static HashMap<String, Object> customers;
    public static HashMap<String, Object> owners;

    //This should be the only class that can access password data directly
    private static HashMap<String, Object> passwords;

    //This method gets the initial data of the database
    //and should be called when the program starts
    public static void init(){


        //Get the initial read of the database and setup to
        //read from the database using a persistent listener
        initCustomers();
        initOwners();
        initPasswords();

    }

    private static void initCustomers(){

        //Setup the initial read for the customers data
        DatabaseUtils.CUSTOMER_ACCOUNTS_REF.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("demo", "Error getting data", task.getException());
                }
                else {
                    if(task.getResult().getValue() != null) {
                        DatabaseManager.customers = (HashMap<String, Object>) task.getResult().getValue();
                    } else {
                        DatabaseManager.customers = null;
                    }
                }
            }
        });

        //Setup to read customer data using a persistent listener
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("demo", "customer data changed");

                if(dataSnapshot.getValue() != null) {
                    DatabaseManager.customers = (HashMap<String, Object>) dataSnapshot.getValue();
                    Log.i("demo", dataSnapshot.getValue().toString()); //Test
                } else {
                    DatabaseManager.customers = null;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled", databaseError.toException());
            }
        };
        DatabaseUtils.CUSTOMER_ACCOUNTS_REF.addValueEventListener(listener);

    }

    private static void initOwners(){

        //Setup the initial read for the owner data
        DatabaseUtils.OWNER_ACCOUNTS_REF.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("demo", "Error getting data", task.getException());
                }
                else {
                    if(task.getResult().getValue() != null) {
                        DatabaseManager.owners = (HashMap<String, Object>) task.getResult().getValue();
                    } else {
                        DatabaseManager.owners = null;
                    }
                }
            }
        });

        //Setup to read owner data using a persistent listener
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("demo", "owner data changed");

                if(dataSnapshot.getValue() != null) {
                    DatabaseManager.owners = (HashMap<String, Object>) dataSnapshot.getValue();
                } else {
                    DatabaseManager.owners = null;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled", databaseError.toException());
            }
        };
        DatabaseUtils.OWNER_ACCOUNTS_REF.addValueEventListener(listener);

    }

    private static void initPasswords(){

        //Setup the initial read for the password data
        DatabaseUtils.PASSWORDS_REF.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("demo", "Error getting data", task.getException());
                }
                else {
                    if(task.getResult().getValue() != null) {
                        DatabaseManager.passwords = (HashMap<String, Object>) task.getResult().getValue();
                    } else {
                        DatabaseManager.passwords = null;
                    }
                }
            }
        });

        //Setup to read password data using a persistent listener
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("demo", "passwords data changed");

                if(dataSnapshot.getValue() != null) {
                    DatabaseManager.passwords = (HashMap<String, Object>) dataSnapshot.getValue();
                    Log.i("demo", dataSnapshot.getValue().getClass().getName()); //Test
                    Log.i("demo", dataSnapshot.getValue().toString()); //Test
                } else {
                    DatabaseManager.passwords = null;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled", databaseError.toException());
            }
        };
        DatabaseUtils.PASSWORDS_REF.addValueEventListener(listener);

    }

    //Return true if the login credentials are valid and false otherwise
    public static boolean validateLogin(String username, String password){
        if(!passwords.containsKey(username))
            return false;
        String correctPassword = (String) passwords.get(username);
        return password.equals(correctPassword);
    }


}

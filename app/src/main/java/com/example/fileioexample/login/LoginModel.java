package com.example.fileioexample.login;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.fileioexample.account.CustomerAccount;
import com.example.fileioexample.utils.DatabaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginModel implements LoginContract.Model{

    public LoginModel(){
        //Get the initial read of the database and setup to
        //read from the database using a persistent listener
        initCustomers();
        initOwners();
        initPasswords();
    }

    public static HashMap<String, Object> customers;
    public static HashMap<String, Object> owners;

    //This should be the only class that can access password data directly
    private static HashMap<String, Object> passwords;

    private void initCustomers(){

        //Setup the initial read for the customers data
        DatabaseUtils.CUSTOMER_ACCOUNTS_REF.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("demo", "Error getting data", task.getException());
                }
                else {
                    if(task.getResult().getValue() != null) {
                        LoginModel.customers = (HashMap<String, Object>) task.getResult().getValue();
                    } else {
                        LoginModel.customers = null;
                    }
                }
            }
        });

        //Setup to read customer data using a persistent listener
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("demo", "customer data changed"); //Test

                if(dataSnapshot.getValue() != null) {
                    LoginModel.customers = (HashMap<String, Object>) dataSnapshot.getValue();
                    Log.i("demo", "customer data: " + dataSnapshot.getValue().toString()); //Test
                } else {
                    LoginModel.customers = null;
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
                        LoginModel.owners = (HashMap<String, Object>) task.getResult().getValue();
                    } else {
                        LoginModel.owners = null;
                    }
                }
            }
        });

        //Setup to read owner data using a persistent listener
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("demo", "owner data changed"); //Test

                if(dataSnapshot.getValue() != null) {
                    LoginModel.owners = (HashMap<String, Object>) dataSnapshot.getValue();
                    Log.i("demo", "owner data: " + dataSnapshot.getValue().toString()); //Test
                } else {
                    LoginModel.owners = null;
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
                        LoginModel.passwords = (HashMap<String, Object>) task.getResult().getValue();
                    } else {
                        LoginModel.passwords = null;
                    }
                }
            }
        });

        //Setup to read password data using a persistent listener
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("demo", "passwords data changed"); //Test

                if(dataSnapshot.getValue() != null) {
                    LoginModel.passwords = (HashMap<String, Object>) dataSnapshot.getValue();
                    Log.i("demo", "passwords data: " + dataSnapshot.getValue().toString()); //Test
                } else {
                    LoginModel.passwords = null;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled", databaseError.toException());
            }
        };
        DatabaseUtils.PASSWORDS_REF.addValueEventListener(listener);

    }

    //Return the account type given the username
    @Override
    public String accountType(String username) {

        if(customers.containsKey(username)){
            return "customer";
        } else if(owners.containsKey(username)){
            return "owner";
        } else {
            return "not found";
        }

    }

    //Return true if the login credentials are valid and false otherwise
    @Override
    public boolean validateLogin(String username, String password){
        if(passwords == null || !passwords.containsKey(username))
            return false;
        String correctPassword = (String) passwords.get(username);
        return password.equals(correctPassword);
    }

}

package com.example.fileioexample;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Account{
    private String username;
    private String password;

    public static boolean check_valid(String n,String acctype){
        final Boolean[] valid = {true};

        DatabaseReference ref =
                FirebaseDatabase.getInstance().getReference();
        ref.child(acctype).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.i("demo", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("demo", "Failed to read value.", error.toException());
            }
        });

        return valid[0];
    }

    public static boolean check_exist(String n, String p,String acctype){
        final Boolean[] valid = {true};
        DatabaseReference ref =
                FirebaseDatabase.getInstance().getReference("Customer Acc");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("demo", "data changed");
                for(DataSnapshot child:dataSnapshot.getChildren()) {
                    String name = child.getValue(String.class);
                    if(name==n){
                        valid[0] = false;
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled",
                        databaseError.toException());
            }
        };
        return valid[0];
    }

    public static String create(String n, String p,String acctype) throws Exception{
        if (check_valid(n,acctype)){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(acctype).child(n).child("Password");
            myRef.setValue(p);
            return n;
        }
        else{
            throw new Exception("User name already exists");
        }
    }

    public static String login(String n, String p,String acctype) throws Exception{
        if (check_exist(n,p,acctype)){
            return n;
        }
        else{
            throw new Exception("User name already exists");
        }
    }
}


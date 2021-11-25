package com.example.fileioexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MSG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // these are for jumping into new activities after login or register
    public void StoreRegister(View view){
        //not updated
    }
    public void StoreLogin(View view){
        //not updated
    }

    public void CreateAccount(View view){
        //Start the create account view
        Intent intent = new Intent(this, CustomerAccountCreationActivity.class);
        startActivity(intent);
    }

    public void CustomerLogin(View view){
        //jump to some view
        Intent intent = new Intent(this,ListProd.class);
        startActivity(intent);
    }

}
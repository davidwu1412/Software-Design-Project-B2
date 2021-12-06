package com.example.fileioexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fileioexample.utils.CurrentUser;
import com.example.fileioexample.utils.DatabaseUtils;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MSG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // these are for jumping into new activities after login or register
    public void startApp(View view){
        //jump to some view to test your activities
        //CurrentUser.ownerUsername = "test4";
        //DatabaseUtils.setupCurrentStore();
        //CurrentUser.customerUsername = "test3";
        //Intent intent = new Intent(this,Cust_Prod.class);
        //Intent intent = new Intent(this,Cust_Order.class);
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

}
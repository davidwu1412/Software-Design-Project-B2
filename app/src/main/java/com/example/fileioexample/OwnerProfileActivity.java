package com.example.fileioexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fileioexample.utils.CurrentUser;

public class OwnerProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_ownerprofile);

        setupCustomerProfileText();
    }

    //Set the text fields of the profile screen
    private void setupCustomerProfileText(){
        TextView usernameText = (TextView) findViewById(R.id.ownerProfile_username);
        usernameText.setText(CurrentUser.username);
        TextView storeNameText = (TextView) findViewById(R.id.ownerProfile_storeName);
        storeNameText.setText(CurrentUser.store.getName());
        TextView storeAddressText = (TextView) findViewById(R.id.ownerProfile_storeAddress);
        storeAddressText.setText(CurrentUser.store.getAddress());
    }

}
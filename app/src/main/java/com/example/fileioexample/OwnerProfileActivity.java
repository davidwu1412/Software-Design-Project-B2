package com.example.fileioexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.fileioexample.utils.CurrentUser;
import com.example.fileioexample.utils.NavigationUtils;
import com.google.android.material.navigation.NavigationView;

public class OwnerProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_ownerprofile);

        setupCustomerProfileText();

        NavigationUtils.currentActivity = this;
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_list_products){
                    NavigationUtils.ownerClickListProduct();
                }
                if(item.getItemId()==R.id.nav_see_all_orders){
                    NavigationUtils.ownerClickSeeAllOrders();
                }
                if(item.getItemId()==R.id.nav_profile){
                    NavigationUtils.ownerClickProfile();
                }
                if(item.getItemId()==R.id.nav_logout){
                    NavigationUtils.ownerClickLogout();
                }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        NavigationUtils.currentActivity = this;
    }

    //Set the text fields of the profile screen
    private void setupCustomerProfileText(){
        TextView usernameText = (TextView) findViewById(R.id.ownerProfile_username);
        usernameText.setText(CurrentUser.ownerUsername);
        TextView storeNameText = (TextView) findViewById(R.id.ownerProfile_storeName);
        storeNameText.setText(CurrentUser.store.getName());
        TextView storeAddressText = (TextView) findViewById(R.id.ownerProfile_storeAddress);
        storeAddressText.setText(CurrentUser.store.getAddress());
    }

}
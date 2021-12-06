package com.example.fileioexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import Current.Current_Order;

public class Cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_origin2);

        Intent intent = getIntent();
        Current_Order Order = new Current_Order();

        String Owner = Current_Order.Owner;
        TextView textView15 = findViewById(R.id.textView18);
        textView15.setText("Store: "+ Owner);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.customerProfile){
                    //NOT FINISHED
                }
                if(item.getItemId()==R.id.seeAllStores){
                    //NOT FINISHED
                }
                if(item.getItemId()==R.id.logoutCart){
                    Click_Logout();
                }
                if(item.getItemId()==R.id.uncompleteOrder){
                    //NOT FINISHED
                }
                return true;
            }
        });
    }

    public void Click_Logout(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }


}
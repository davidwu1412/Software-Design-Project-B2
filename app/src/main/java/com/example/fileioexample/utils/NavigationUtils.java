package com.example.fileioexample.utils;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fileioexample.CustomerProfileActivity;
import com.example.fileioexample.ListStores;
import com.example.fileioexample.LoginActivity;
import com.example.fileioexample.OwnerListProductsActivity;
import com.example.fileioexample.OwnerOrdersList;
import com.example.fileioexample.OwnerProfileActivity;
import com.example.fileioexample.R;
import com.example.fileioexample.store.Order;
import com.google.android.material.navigation.NavigationView;

import java.util.Collections;

public class NavigationUtils {

    public static AppCompatActivity currentActivity;
    private static final boolean ALLOW_SELF_NAVIGATION = true;

    public static void setupOwnerNavigationMenu(AppCompatActivity activity){
        NavigationUtils.currentActivity = activity;
        NavigationView navigationView = currentActivity.findViewById(R.id.nav_view);
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

    public static void ownerClickListProduct(){
        if(!(currentActivity instanceof OwnerListProductsActivity) || ALLOW_SELF_NAVIGATION) {
            //DatabaseUtils.updateCurrentStoreOrders();
            Intent intent = new Intent(currentActivity, OwnerListProductsActivity.class);
            currentActivity.startActivity(intent);
        }
    }

    public static void ownerClickSeeAllOrders(){
        if(!(currentActivity instanceof OwnerOrdersList) || ALLOW_SELF_NAVIGATION) {
            //DatabaseUtils.updateCurrentStoreOrders();
            CurrentUser.store.getOrdersList().removeAll(Collections.singleton(null));
            Intent intent = new Intent(currentActivity, OwnerOrdersList.class);
            currentActivity.startActivity(intent);
        }
    }

    public static void ownerClickProfile(){
        if(!(currentActivity instanceof OwnerProfileActivity) || ALLOW_SELF_NAVIGATION) {
            //DatabaseUtils.updateCurrentStoreOrders();
            Intent intent = new Intent(currentActivity, OwnerProfileActivity.class);
            currentActivity.startActivity(intent);
        }
    }

    public static void ownerClickLogout(){
        Intent intent = new Intent(currentActivity, LoginActivity.class);
        currentActivity.startActivity(intent);
    }

    public static void setupCustomerNavigationMenu(AppCompatActivity activity){
        NavigationUtils.currentActivity = activity;
        NavigationView navigationView = currentActivity.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.seeAllStores){
                    NavigationUtils.customerClickSeeAllStores();
                }
                if(item.getItemId()==R.id.customerProfile){
                    NavigationUtils.customerClickProfile();
                }
                if(item.getItemId()==R.id.logoutCart){
                    NavigationUtils.customerClickLogout();
                }
                return true;
            }
        });
    }

    public static void customerClickSeeAllStores(){
        if(!(currentActivity instanceof ListStores) || ALLOW_SELF_NAVIGATION) {
            //DatabaseUtils.updateCurrentStoreOrders();
            CurrentUser.cart.clear();
            Intent intent = new Intent(currentActivity, ListStores.class);
            currentActivity.startActivity(intent);
        }
    }

    public static void customerClickProfile(){
        if(!(currentActivity instanceof CustomerProfileActivity) || ALLOW_SELF_NAVIGATION) {
            //DatabaseUtils.updateCurrentStoreOrders();
            Intent intent = new Intent(currentActivity, CustomerProfileActivity.class);
            currentActivity.startActivity(intent);
        }
    }

    public static void customerClickLogout(){
        Intent intent = new Intent(currentActivity, LoginActivity.class);
        currentActivity.startActivity(intent);
    }

}

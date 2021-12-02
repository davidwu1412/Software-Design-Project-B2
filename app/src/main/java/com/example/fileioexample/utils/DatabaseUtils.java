package com.example.fileioexample.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.fileioexample.account.CustomerAccount;
import com.example.fileioexample.account.OwnerAccount;
import com.example.fileioexample.login.LoginModel;
import com.example.fileioexample.store.Order;
import com.example.fileioexample.store.Product;
import com.example.fileioexample.store.Store;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUtils {

    public static final DatabaseReference ACCOUNTS_REF = FirebaseDatabase.getInstance().getReference("accounts");
    public static final DatabaseReference CUSTOMER_ACCOUNTS_REF = ACCOUNTS_REF.child("customers");
    public static final DatabaseReference OWNER_ACCOUNTS_REF = ACCOUNTS_REF.child("owners");
    public static final DatabaseReference PASSWORDS_REF = ACCOUNTS_REF.child("passwords");

    public static final DatabaseReference STORES_REFERENCE = FirebaseDatabase.getInstance().getReference("stores");


    //This method checks if a username is taken by an existing account in the database
    //It returns true if the username is valid (not taken) and false if it is taken
    public static boolean validateUsername(String username){

        //Check the username is being used by a customer
        if(LoginModel.customers != null && LoginModel.customers.containsKey(username)){
            return false;
        }

        //Check the username is being used by an owner
        if(LoginModel.owners != null && LoginModel.owners.containsKey(username)){
            return false;
        }

        //If the username is not in use, return true
        return true;

    }

    //This method checks if a store address is taken by an existing account in the database
    //It returns true if the address is valid (not taken) and false if it is taken
    public static boolean validateNewStoreAddress(String newStoreName, String newStoreAddress){

        //If there are no owners, return true
        if(LoginModel.owners == null)
            return true;

        for (Map.Entry entry : LoginModel.owners.entrySet()) {

            //Get the address from each owner
            HashMap owner = (HashMap) entry.getValue();
            String storeName = (String) owner.get("storeName");
            String address = (String) owner.get("storeAddress");

            //If the address matches newStoreAddress, the address is taken, so return false
            if(newStoreName.equals(storeName) && newStoreAddress.equals(address)){
                Log.i("demo", "store name and address taken"); //Test
                return false;
            }
        }

        //Return true if the address is not found
        return true;

    }

    //Write customer account to the database
    public static void addCustomerAccount(String username, String password){

        DatabaseUtils.CUSTOMER_ACCOUNTS_REF.child(username).setValue(new CustomerAccount(username));
        DatabaseUtils.PASSWORDS_REF.child(username).setValue(password);

    }

    //Write an owner account to the database
    public static void addOwnerAccount(String username, String storeName, String storeAddress, String password){

        DatabaseUtils.OWNER_ACCOUNTS_REF.child(username).setValue(new OwnerAccount(username, storeName, storeAddress));
        DatabaseUtils.PASSWORDS_REF.child(username).setValue(password);

    }

    //Use the value of username in CurrentUser class
    public static void setupCurrentStore(){

        CurrentUser.store = new Store();

        //Setup the initial read for the storeName and storeAddress
        DatabaseUtils.OWNER_ACCOUNTS_REF.child(CurrentUser.username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("demo", "Error getting data", task.getException());
                }
                else {
                    if(task.getResult().getValue() != null) {
                        HashMap<String, Object> ownerData = (HashMap<String, Object>) task.getResult().getValue();
                        CurrentUser.store.setName((String) ownerData.get("storeName"));
                        CurrentUser.store.setAddress((String) ownerData.get("storeAddress"));
                    }
                }
            }
        });

        //Setup the initial read for the owner data
        /*DatabaseUtils.STORES_REFERENCE.child(CurrentUser.username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("demo", "Error getting data", task.getException());
                }
                else {
                    if(task.getResult().getValue() != null) {
                        HashMap<String, Object> storeData = (HashMap<String, Object>) task.getResult().getValue();

                        //TEST
                        Log.i("demo", "storeData availableProducts class type = " + storeData.get("availableProducts").getClass().getName());
                        Log.i("demo", "storeData availableProducts = " + storeData.get("availableProducts").toString());

                        //CurrentUser.store.setAvailableProducts((ArrayList<Product>)storeData.get("availableProducts"));

                        //Fill the products
                        HashMap<String, Object> availableProducts = (HashMap<String, Object>) storeData.get("availableProducts"); //This is apparently an arraylist
                        for(Map.Entry<String, Object> entry: availableProducts.entrySet()){
                            HashMap<String, Object> productMap = (HashMap<String, Object>) entry.getValue();
                            String brand = (String) productMap.get("brand");
                            String name = (String) productMap.get("name");
                            double price = (double) productMap.get("price");
                            Product product = new Product(name, brand, price);
                            CurrentUser.store.getAvailableProducts().add(product);
                        }

                        //TEST
                        Log.i("demo", "storeData orderList class type = " + storeData.get("orderList").getClass().getName());
                        Log.i("demo", "storeData orderList = " + storeData.get("orderList").toString());

                        //CurrentUser.store.setOrdersList((ArrayList<Order>)storeData.get("orderList"));

                        //Fill the orders
                        HashMap<String, Object> orderListMap = (HashMap<String, Object>) storeData.get("orderList");
                        for(Map.Entry<String, Object> entry: orderListMap.entrySet()){

                            HashMap<String, Object> orderMap = (HashMap<String, Object>) entry.getValue();
                            String customerName = (String) orderMap.get("customerName");
                            boolean fulfilled = (boolean) orderMap.get("fulfilled");
                            int orderNumber = (int) orderMap.get("orderNumber");
                            double totalCost = (double) orderMap.get("totalCost");
                            ArrayList<Product> products = new ArrayList<Product>();

                            HashMap<String, Object> orderProducts = (HashMap<String, Object>) orderListMap.get("products");
                            for(Map.Entry<String, Object> productEntry: orderProducts.entrySet()){
                                HashMap<String, Object> productMap = (HashMap<String, Object>) entry.getValue();
                                String brand = (String) productMap.get("brand");
                                String name = (String) productMap.get("name");
                                double price = (double) productMap.get("price");
                                Product product = new Product(name, brand, price);
                                products.add(product);
                            }

                            Order order = new Order(products, totalCost, fulfilled, customerName, orderNumber);
                            CurrentUser.store.getOrdersList().add(order);
                        }

                    }
                }
            }
        });*/

        //Setup the initial read for the owner data
        DatabaseUtils.STORES_REFERENCE.child(CurrentUser.username).child("availableProducts").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("demo", "Error getting data", task.getException());
                }
                else {
                    if(task.getResult().getValue() != null) {
                        GenericTypeIndicator<ArrayList<Product>> t = new GenericTypeIndicator<ArrayList<Product>>() {};
                        CurrentUser.store.setAvailableProducts(task.getResult().getValue(t));
                    }
                }
            }
        });

        DatabaseUtils.STORES_REFERENCE.child(CurrentUser.username).child("orderList").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("demo", "Error getting data", task.getException());
                }
                else {
                    if(task.getResult().getValue() != null) {
                        GenericTypeIndicator<ArrayList<Order>> t = new GenericTypeIndicator<ArrayList<Order>>() {};
                        CurrentUser.store.setOrdersList(task.getResult().getValue(t));
                    }
                }
            }
        });

        //Since we are using the orderNumber as the key for each element of orderList in the
        //database, we must remove all of the null indices after reading the data
        CurrentUser.store.getOrdersList().removeAll(Collections.singleton(null));

    }

    public static void writeStoreToDatabase(String username, Store store) {

        writeAvailableProductsToDatabase(username, store);
        writeOrderListToDatabase(username, store);

    }

    public static void writeAvailableProductsToDatabase(String username, Store store){
        DatabaseReference storeRef = DatabaseUtils.STORES_REFERENCE.child(username);
        DatabaseReference availableProductsRef = storeRef.child("availableProducts");

        if(store.getAvailableProducts() != null)
            availableProductsRef.setValue(store.getAvailableProducts());
    }

    public static void writeOrderListToDatabase(String username, Store store){
        DatabaseReference storeRef = DatabaseUtils.STORES_REFERENCE.child(username);
        DatabaseReference orderListRef = storeRef.child("orderList");

        /*
        if(store.getOrdersList() != null)
            storeRef.child("orderList").setValue(store.getOrdersList());
        */

        //This code sets the key of each order to in orderList to be orderNumber when
        //writing to the database
        for(Order currentOrder: store.getOrdersList()){
            DatabaseReference currentOrderRef = orderListRef.child(Integer.toString(currentOrder.getOrderNumber()));
            currentOrderRef.setValue(currentOrder);
        }
    }

}

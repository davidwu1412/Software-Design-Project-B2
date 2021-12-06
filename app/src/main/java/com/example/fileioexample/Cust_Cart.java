package com.example.fileioexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.fileioexample.store.Order;
import com.example.fileioexample.store.OrderToken;
import com.example.fileioexample.store.Store;
import com.example.fileioexample.utils.CurrentUser;
import com.example.fileioexample.utils.DatabaseUtils;
import com.example.fileioexample.utils.NavigationUtils;
import com.example.fileioexample.utils.Popup;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class Cust_Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    //DatabaseReference database;
    CustCartAdapter adapter;
    //ArrayList<CustProduct> cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_cust_cart);


        recyclerView = findViewById(R.id.CustCartRecyclerView);
        //database = FirebaseDatabase.getInstance().getReference("stores/owner1/orderList/0/products");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CustCartAdapter(this, CurrentUser.cart);
        recyclerView.setAdapter(adapter);

        /*cart = new ArrayList<CustProduct>();
        adapter = new CustCartAdapter(this, cart);
        recyclerView.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapShot:snapshot.getChildren()){
                    CustProduct p = dataSnapShot.getValue(CustProduct.class);

                    cart.add(p);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        NavigationUtils.setupCustomerNavigationMenu(this);
    }

    public void finishOrder(int orderId){
        Popup.createNewAlertPopup("Order successfully placed. Your order ID# is " + orderId, this);
    }

    public void checkout(View view){

        if(CurrentUser.cart.isEmpty()) {
            Popup.createNewAlertPopup("Your cart is empty", this);
            return;
        }

        FirebaseDatabase.getInstance().getReference("orderId").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("demo", "Error getting data", task.getException());
                }
                else {
                    if(task.getResult().getValue() != null) {
                        //Create the order and add it to the store's order list
                        int orderId = task.getResult().getValue(int.class);
                        Order order = new Order();
                        order.setFulfilled(false);
                        order.setOrderId(orderId);
                        order.setCustomerUsername(CurrentUser.customerUsername);
                        order.setProducts(CurrentUser.cart);
                        order.updateTotalCost();
                        CurrentUser.store.getOrdersList().add(order);
                        OrderToken orderToken = new OrderToken(CurrentUser.ownerUsername, orderId);

                        //Write to the database
                        //Update the orderId by incrementing it by 1
                        FirebaseDatabase.getInstance().getReference("orderId").setValue(orderId + 1);
                        //Write the order token
                        FirebaseDatabase.getInstance().getReference("orders").
                                child(CurrentUser.customerUsername).child("orderTokens").
                                child(Integer.toString(orderId)).setValue(orderToken);
                        //Write the orderList for the selected store
                        DatabaseUtils.writeOrderListToDatabase(CurrentUser.ownerUsername, CurrentUser.store);

                        //Clear the cart
                        CurrentUser.cart.clear();
                        adapter.notifyDataSetChanged();
                        finishOrder(orderId);
                    }
                }
            }
        });

    }

     public void gotoCust_Order(View view){
        Intent intent = new Intent(this,Cust_Order.class);
        startActivity(intent);
     }

    public void gotoCust_Prod(View view){
        Intent intent = new Intent(this,Cust_Prod.class);
        startActivity(intent);
    }
}
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
import com.example.fileioexample.store.Product;
import com.example.fileioexample.utils.CurrentUser;
import com.example.fileioexample.utils.DatabaseUtils;
import com.example.fileioexample.utils.NavigationUtils;
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

public class Cust_Order extends AppCompatActivity {

    RecyclerView recyclerView;
    //DatabaseReference database;
    CustOrderAdapter adapter;
    //ArrayList<CustOrder> Order;
    ArrayList<Order> orders;
    ArrayList<OrderToken> orderTokens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_cust_order);

        recyclerView = findViewById(R.id.CustOrderRecyclerView);
        //database = FirebaseDatabase.getInstance().getReference("stores/owner1/orderList");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderTokens = new ArrayList<OrderToken>();
        orders = new ArrayList<Order>();

        adapter = new CustOrderAdapter(this, orders);
        recyclerView.setAdapter(adapter);

        //Order = new ArrayList<CustOrder>();
        //adapter = new CustOrderAdapter(this,Order);
        //recyclerView.setAdapter(adapter);

        /*database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapShot:snapshot.getChildren()){
                    CustOrder o = dataSnapShot.getValue(CustOrder.class);
                    Order.add(o);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        FirebaseDatabase.getInstance().getReference("orders/"+CurrentUser.customerUsername+"/orderTokens").
                get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("demo", "Error getting data", task.getException());
                }
                else {
                    if(task.getResult().getValue() != null) {
                        GenericTypeIndicator<ArrayList<OrderToken>> t = new GenericTypeIndicator<ArrayList<OrderToken>>() {};
                        orderTokens = task.getResult().getValue(t);
                        orderTokens.removeAll(Collections.singleton(null));
                        updateOrderScreen();
                    }
                }
            }
        });

        //Setup a persistent listener to check if orders are fulfilled
        FirebaseDatabase.getInstance().getReference("stores/"+CurrentUser.ownerUsername+"/orderList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GenericTypeIndicator<ArrayList<Order>> t = new GenericTypeIndicator<ArrayList<Order>>() {};
                CurrentUser.store.setOrdersList(snapshot.getValue(t));
                CurrentUser.store.getOrdersList().removeAll(Collections.singleton(null));
                orders.clear();
                updateOrderScreen();
                Log.i("demo", "checked orders");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        NavigationUtils.setupCustomerNavigationMenu(this);
    }

    public void updateOrderScreen(){
        for(OrderToken orderToken : orderTokens){
            for(Order order : CurrentUser.store.getOrdersList()){
                if(order.getOrderId() == orderToken.getOrderId())
                    orders.add(order);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void gotoCust_Prod(View view){
        Intent intent = new Intent(this,Cust_Prod.class);
        startActivity(intent);
    }

    public void gotoCust_Cart(View view){
        Intent intent = new Intent(this,Cust_Cart.class);
        startActivity(intent);
    }
}
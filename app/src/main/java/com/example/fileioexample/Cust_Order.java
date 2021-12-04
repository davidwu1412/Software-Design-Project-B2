package com.example.fileioexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fileioexample.store.Order;
import com.example.fileioexample.store.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cust_Order extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    CustOrderAdapter adapter;
    ArrayList<CustOrder> Order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_cust_order);

        recyclerView = findViewById(R.id.CustOrderRecyclerView);
        database = FirebaseDatabase.getInstance().getReference("stores/owner1/orderList");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Order = new ArrayList<CustOrder>();
        adapter = new CustOrderAdapter(this,Order);
        recyclerView.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
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
        });
    }

    public void gotoCust_StoreList(View view){
        Intent intent = new Intent(this,Cust_StoreList.class);
        startActivity(intent);
    }

    public void gotoCust_Cart(View view){
        Intent intent = new Intent(this,Cust_Cart.class);
        startActivity(intent);
    }
}
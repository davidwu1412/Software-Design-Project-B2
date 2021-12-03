package com.example.fileioexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fileioexample.store.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cust_Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    CustCartAdapter adapter;
    ArrayList<CustProduct> Cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_cart);


        recyclerView = findViewById(R.id.CustCartRecyclerView);
        database = FirebaseDatabase.getInstance().getReference("stores/owner1/orderList/0/products");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cart = new ArrayList<CustProduct>();
        adapter = new CustCartAdapter(this,Cart);
        recyclerView.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapShot:snapshot.getChildren()){
                    CustProduct p = dataSnapShot.getValue(CustProduct.class);

                    Cart.add(p);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

     public void gotoCust_Order(View view){
        Intent intent = new Intent(this,Cust_Order.class);
        startActivity(intent);
     }

    public void gotoCust_StoreList(View view){
        Intent intent = new Intent(this,Cust_StoreList.class);
        startActivity(intent);
    }
}
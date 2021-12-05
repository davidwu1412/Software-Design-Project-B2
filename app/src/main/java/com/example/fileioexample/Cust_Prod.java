package com.example.fileioexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fileioexample.store.Product;
import com.example.fileioexample.utils.CurrentUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cust_Prod extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    CustProdAdapter adapter;
    ArrayList<Product> availableProducts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_cust_prod);

        recyclerView = findViewById(R.id.CustProdRecyclerView);
        //database = FirebaseDatabase.getInstance().getReference("stores/owner1/availableProducts");
        database = FirebaseDatabase.getInstance().getReference("stores/" + CurrentUser.ownerUsername + "/availableProducts");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        availableProducts = new ArrayList<Product>();
        adapter = new CustProdAdapter(this, availableProducts);
        recyclerView.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapShot:snapshot.getChildren()){
                    Product p = dataSnapShot.getValue(Product.class);

                    availableProducts.add(p);
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

    public void gotoCust_Cart(View view){
        Intent intent = new Intent(this,Cust_Cart.class);
        startActivity(intent);
    }

}

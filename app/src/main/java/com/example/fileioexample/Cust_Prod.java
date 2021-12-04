package com.example.fileioexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
    ArrayList<CustStoreProd> Cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_cust_prod);

        recyclerView = findViewById(R.id.CustProdRecyclerView);
        database = FirebaseDatabase.getInstance().getReference("stores/owner1/availableProducts");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cart = new ArrayList<CustStoreProd>();
        adapter = new CustProdAdapter(this,Cart);
        recyclerView.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapShot:snapshot.getChildren()){
                    CustStoreProd p = dataSnapShot.getValue(CustStoreProd.class);

                    Cart.add(p);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}

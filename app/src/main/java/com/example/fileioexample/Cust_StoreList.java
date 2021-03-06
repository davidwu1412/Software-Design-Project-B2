package com.example.fileioexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fileioexample.store.Store;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class Cust_StoreList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    CustStoreAdapter adapter;
    ArrayList<CustStore> StoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_store_list);

        recyclerView = findViewById(R.id.CustStoreRecyclerView);
        database = FirebaseDatabase.getInstance().getReference("accounts/owners");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StoreList = new ArrayList<CustStore>();
        adapter = new CustStoreAdapter(this,StoreList);
        recyclerView.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapShot:snapshot.getChildren()){
                    CustStore s = dataSnapShot.getValue(CustStore.class);
                    StoreList.add(s);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void gotoCust_Cart(View view){
        Intent intent = new Intent(this,Cust_Cart.class);
        startActivity(intent);
    }
    public void gotoCust_Order (View view){
        Intent intent = new Intent(this,Cust_Order.class);
        startActivity(intent);
    }

    public void gotoProd_List(View view){
        Intent intent = new Intent(this,Cust_Prod.class);
        startActivity(intent);
    }
}
package com.example.fileioexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fileioexample.account.OwnerAccount;
import com.example.fileioexample.store.Product;
import com.example.fileioexample.store.Store;
import com.example.fileioexample.utils.CurrentUser;
import com.example.fileioexample.utils.DatabaseUtils;
import com.example.fileioexample.utils.NavigationUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Current.Current_Store;

public class ListStores extends AppCompatActivity implements StoresListAdapter.OnStoreListener {


    private RecyclerView recyclerView;
    StoresListAdapter adapter;
    DatabaseReference ref;
    //private List<StoreObj> storeObjList = new ArrayList<>();
    private ArrayList<OwnerAccount> storeList = new ArrayList<OwnerAccount>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_liststores);

        ref = FirebaseDatabase.getInstance().getReference("/accounts/owners/");
        recyclerView = findViewById(R.id.recycler_store);

        // displays the recyclerview linearly(vertical)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                storeList.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    OwnerAccount storeObj = dataSnapshot1.getValue(OwnerAccount.class);
                    storeList.add(storeObj);
                }
                adapter = new StoresListAdapter(ListStores.this, storeList, ListStores.this::onStoreClick);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /*ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                storeObjList.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    StoreObj storeObj = dataSnapshot1.getValue(StoreObj.class);
                    storeObjList.add(storeObj);
                }
                adapter = new StoresListAdapter(ListStores.this, storeObjList, ListStores.this::onStoreClick);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        NavigationUtils.setupCustomerNavigationMenu(this);
    }

    @Override
    public void onStoreClick(int position) {
        //String a = storeObjList.get(position).getStoreAddress();//gives reference to the object selected in the activity
        //Current_Store.setStoreName(storeObjList.get(position).getStoreName());
        //Current_Store.setAddress(storeObjList.get(position).getStoreAddress());
        CurrentUser.ownerUsername = storeList.get(position).getUsername();
        DatabaseUtils.setupCurrentStore();
        CurrentUser.cart = new ArrayList<Product>();
        Intent intent = new Intent(this, Cust_Prod.class);
        startActivity(intent);

    }

}
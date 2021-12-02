package com.example.fileioexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.fileioexample.store.Product;
import com.example.fileioexample.store.Store;
import com.example.fileioexample.ui.ownerListProducts.OwnerListProductsAdapter;
import com.example.fileioexample.utils.CurrentUser;

public class OwnerListProductsActivity extends AppCompatActivity {

    private String ownerName;
    private Store store;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_list_products);

        ownerName = CurrentUser.username;

        //Setup the store field here
        CurrentUser.store = new Store();
        //Read from the database to set the fields for store

        /*
        store = new Store();
        for(int i = 0; i<20; i++)
            store.getAvailableProducts().add(new Product("chips", "lays", 0.99));
        store.getAvailableProducts().add(new Product("cola", "pepsi", 1.99));
         */
        //////////////////////////////////////////

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        OwnerListProductsAdapter adapter = new OwnerListProductsAdapter(this, store);
        recyclerView.setAdapter(adapter);

    }
}
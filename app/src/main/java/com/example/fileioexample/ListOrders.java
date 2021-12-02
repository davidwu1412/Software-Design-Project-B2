package com.example.fileioexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListOrders extends AppCompatActivity {

    private RecyclerView recyclerView;
    OrdersListAdapter adapter;
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_orders);

        //need to change path here, find the username of the current store we are in
        mbase = FirebaseDatabase.getInstance().getReference("/stores/owner1/orderList/");
        recyclerView = findViewById(R.id.listOrders_recycler);

        // displays the recyclerview linearly(vertical)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // makes query into database to obtain the required data
        FirebaseRecyclerOptions<OrderObj> options = new FirebaseRecyclerOptions.Builder<OrderObj>().setQuery(mbase, OrderObj.class).build();

        //connecting the StoreObj class to the Adapter class
        adapter = new OrdersListAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }




}
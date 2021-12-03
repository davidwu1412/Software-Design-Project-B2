package com.example.fileioexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OwnerOrdersList extends AppCompatActivity implements OwnerOrdersListAdapter.OnOrderListener {

    private RecyclerView recyclerView;
    OwnerOrdersListAdapter adapter;
    DatabaseReference ref;
    private List<OrderObj> orderObjList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_orders_list);


        ref = FirebaseDatabase.getInstance().getReference("/stores/owner1/orderList/");
        recyclerView = findViewById(R.id.ordersList_recycler);

        // displays the recyclerview linearly(vertical)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderObjList.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    OrderObj orderObj = dataSnapshot1.getValue(OrderObj.class);
//                    if (orderObj.fulfilled == "false"){
//                        orderObj.setFulfilled("Incomplete");
//                        //Log.d("COMPLETED", ""+orderObj.fulfilled);
//                        //orderObjList.add(orderObj);
//                    }
                    orderObjList.add(orderObj);
                }
                adapter = new OwnerOrdersListAdapter(OwnerOrdersList.this, orderObjList, OwnerOrdersList.this::onOrdersClick);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onOrdersClick(int position) {
        //String a = orderObjList.get(position).getOrderId(); //gives reference to the object selected in the activity
        Intent intent = new Intent(this, ListStores.class);
        startActivity(intent);

        //Log.d("ADDRESS:", a);
    }

}
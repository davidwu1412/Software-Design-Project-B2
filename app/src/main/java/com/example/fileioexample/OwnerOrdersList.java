package com.example.fileioexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.fileioexample.store.Order;
import com.example.fileioexample.utils.CurrentUser;
import com.example.fileioexample.utils.NavigationUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OwnerOrdersList extends AppCompatActivity implements OwnerOrdersListAdapter.OnOrderListener {

    private RecyclerView recyclerView;
    OwnerOrdersListAdapter adapter;
    DatabaseReference ref;
    //private List<OrderObj> orderObjList = new ArrayList<>();
    //private ArrayList<Order> orderList = new ArrayList<Order>();
    //String ownerName = Current_Owner.getOwnerUsername();
    //String ownerName = "owner1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_ownerorderlist);


        //ref = FirebaseDatabase.getInstance().getReference("/stores/"+ownerName+"/orderList/");
        ref = FirebaseDatabase.getInstance().getReference("/stores/"+ CurrentUser.ownerUsername +"/orderList/");
        recyclerView = findViewById(R.id.ordersList_recycler);

        // displays the recyclerview linearly(vertical)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                orderObjList.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    OrderObj orderObj = dataSnapshot1.getValue(OrderObj.class);
                    orderObjList.add(orderObj);
                }
                adapter = new OwnerOrdersListAdapter(OwnerOrdersList.this, orderObjList, OwnerOrdersList.this::onOrdersClick);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                CurrentUser.store.getOrdersList().clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    Order order = dataSnapshot1.getValue(Order.class);
                    CurrentUser.store.getOrdersList().add(order);
                }
                adapter = new OwnerOrdersListAdapter(OwnerOrdersList.this, CurrentUser.store.getOrdersList(), OwnerOrdersList.this::onOrdersClick);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        adapter = new OwnerOrdersListAdapter(OwnerOrdersList.this, CurrentUser.store.getOrdersList(), OwnerOrdersList.this::onOrdersClick);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        /*for(Order o : CurrentUser.store.getOrdersList())
            if(o != null)
                Log.i("demo", o.toString());
            else
                Log.i("demo", "null");
        */
        NavigationUtils.setupOwnerNavigationMenu(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NavigationUtils.currentActivity = this;
    }

    @Override
    public void onOrdersClick(int position) {
        //updates the static variables so the correct order details of the correct order are opened
        /*Current_Order.setOrderToken(Integer.toString(orderList.get(position).getOrderId()));
        Current_Order.setOwner(CurrentUser.username);
        Current_Order.setCustomer(orderList.get(position).getCustomerUsername());*/

        CurrentUser.currentOrderToken.setOrderId(CurrentUser.store.getOrdersList().get(position).getOrderId());
        CurrentUser.currentOrderToken.setOwner(CurrentUser.ownerUsername);

        Intent intent = new Intent(this, Order_Details.class);
        //Intent intent = new Intent(this, ListStores.class);
        startActivity(intent);

    }

}
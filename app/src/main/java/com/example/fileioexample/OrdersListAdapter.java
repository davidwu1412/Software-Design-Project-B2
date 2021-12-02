package com.example.fileioexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class OrdersListAdapter extends FirebaseRecyclerAdapter<OrderObj, OrdersListAdapter.ordersViewholder> {
    public OrdersListAdapter(@NonNull FirebaseRecyclerOptions<OrderObj> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull OrdersListAdapter.ordersViewholder holder, int position, @NonNull OrderObj model) {


        holder.orderId.setText(model.getOrderId());
        //holder.storeAddress.setText(model.getStoreAddress());
    }


    @NonNull
    @Override
    public OrdersListAdapter.ordersViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrdersListAdapter.ordersViewholder(view);
    }


    public class ordersViewholder extends RecyclerView.ViewHolder{
        TextView orderId;
        public ordersViewholder(@NonNull View itemView) {
            super(itemView);

            orderId = itemView.findViewById(R.id.order_id);
            //storeAddress = itemView.findViewById(R.id.address);
        }
    }

}

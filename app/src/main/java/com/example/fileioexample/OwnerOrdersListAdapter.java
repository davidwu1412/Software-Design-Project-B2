package com.example.fileioexample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OwnerOrdersListAdapter extends RecyclerView.Adapter<OwnerOrdersListAdapter.ordersViewHolder>{

    private List<OrderObj> orderObjList = new ArrayList<>();
    private Context context;
    private OnOrderListener myOnOrderListener;

    public OwnerOrdersListAdapter(Context context, List<OrderObj> orderObjList, OnOrderListener onOrderListener){
        this.context = context;
        this.orderObjList = orderObjList;
        this.myOnOrderListener = onOrderListener;

    }

    @Override
    public void onBindViewHolder(@NonNull OwnerOrdersListAdapter.ordersViewHolder holder, int position) {
        OrderObj orderObj = orderObjList.get(position);
        if (orderObj.getFulfilled().equals("false")){
            orderObj.setFulfilled("Incomplete");
        }else{
            orderObj.setFulfilled("Completed");
        }

        holder.orderId.setText(orderObj.getOrderId());
        holder.customerUsername.setText(orderObj.getCustomerUsername());
        holder.fulfilled.setText(orderObj.getFulfilled());

    }

    @NonNull
    @Override
    public ordersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_item, parent, false);

        return new ordersViewHolder(view, myOnOrderListener);
    }

    @Override
    public int getItemCount(){
        return orderObjList.size();
    }

    public class ordersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView orderId, customerUsername, fulfilled;
        OnOrderListener onOrderListener;

        @Override
        public void onClick(View v) {

            onOrderListener.onOrdersClick(getAdapterPosition());
        }

        public ordersViewHolder(@NonNull View itemView, OwnerOrdersListAdapter.OnOrderListener onOrderListener) {
            super(itemView);

            orderId = itemView.findViewById(R.id.order_id);
            customerUsername = itemView.findViewById(R.id.cus_username);
            fulfilled = itemView.findViewById(R.id.status);
            this.onOrderListener = onOrderListener;

            itemView.setOnClickListener(this);
        }
    }

    //use this interface to interpret/detect the click then use onOrdersClick in the activity to send
    // the position of that clicked item;
    public interface OnOrderListener{
        void onOrdersClick(int position);

    }

}

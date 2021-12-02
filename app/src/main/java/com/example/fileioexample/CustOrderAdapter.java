package com.example.fileioexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.view.View;

import java.util.ArrayList;

public class CustOrderAdapter extends RecyclerView.Adapter<CustOrderAdapter.CustOrderViewHolder> {
    Context context;
    ArrayList<Product> Order;

    public CustOrderAdapter(Context context, ArrayList<Product> Order){
        this.context = context;
        this.Order = Order;
    }

    @NonNull
    @Override
    public CustOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cust_order_layout.parent,false);
        return new CustOrderViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustOrderViewHolder holder, int position) {
        Product p = Order.get(position);
        holder.ProdInfo.setText(p.getInfo);
    }

    @Override
    public int getItemCount() {
        return Order.size();
    }

    public static class CustOrderViewHolder extends RecyclerView.ViewHolder{
        TextView ProdInfo;

        public CustOrderViewHolder(@NonNull View itemView){
            super(itemView);
            ProdInfo = itemView.findViewById(R.id.ProductInfo);


        }
    }
}
package com.example.fileioexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustProdAdapter extends RecyclerView.Adapter<CustProdAdapter.CustProdViewHolder> {
    Context context;
    ArrayList<CustStoreProd> Cart;

    public CustProdAdapter(Context context, ArrayList<CustStoreProd> Cart){
        this.context = context;
        this.Cart = Cart;
    }

    @NonNull
    @Override
    public CustProdAdapter.CustProdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cust_prod_layout,parent,false);
        return new CustProdAdapter.CustProdViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustProdAdapter.CustProdViewHolder holder, int position) {
        CustStoreProd p = Cart.get(position);
        holder.ProdInfo.setText(p.toString());
        holder.PlaceOrder.setText("Place Order");
    }

    @Override
    public int getItemCount() {
        return Cart.size();
    }

    public static class CustProdViewHolder extends RecyclerView.ViewHolder{
        TextView ProdInfo;
        Button PlaceOrder;
        public CustProdViewHolder(@NonNull View itemView){
            super(itemView);
            ProdInfo = itemView.findViewById(R.id.CustProdInfo);
            PlaceOrder = itemView.findViewById(R.id.Add);
        }
    }
}
package com.example.fileioexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fileioexample.store.Product;
import com.google.firebase.database.core.view.View;

import java.util.ArrayList;

public class CustCartAdapter extends RecyclerView.Adapter<CustCartAdapter.CustCartViewHolder> {
    Context context;
    ArrayList<Product> Cart;

    public CustCartAdapter(Context context, ArrayList<Product> Cart){
        this.context = context;
        this.Cart = Cart;
    }

    @NonNull
    @Override
    public CustCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cust_cart_layout.parent,false);
        return new CustCartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustCartViewHolder holder, int position) {
        Product p = Cart.get(position);
        holder.ProdInfo.setText(p.getInfo);
        holder.PlaceOrder.setText("Add");
    }

    @Override
    public int getItemCount() {
        return Cart.size();
    }

    public static class CustCartViewHolder extends RecyclerView.ViewHolder{
        TextView ProdInfo;
        Button PlaceOrder;
        public CustCartViewHolder(@NonNull View itemView){
            super(itemView);
            ProdInfo = itemView.findViewById(R.id.ProductInfo);
            PlaceOrder = itemView.findViewById(R.id.button10);

        }
}
}

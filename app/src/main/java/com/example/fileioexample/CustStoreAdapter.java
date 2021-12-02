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

public class CustStoreAdapter extends RecyclerView.Adapter<CustStoreAdapter.CustStoreViewHolder>{
    Context context;
    ArrayList<Store> StoreList;

    public CustStoreAdapter(Context context, ArrayList<Store> StoreList){
        this.context=context;
        this.StoreList = StoreList;
    }


    @NonNull
    @Override
    public CustStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cust_store_layout.parent,false);
        return new CustStoreViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustStoreViewHolder holder, int position) {
        Store s = StoreList.get(position);
        holder.StoreInfo.setText(s.getInfo);
        holder.Enter.setText("Enter");
    }

    @Override
    public int getItemCount() {
        return StoreList.size();
    }

    public static class CustStoreViewHolder extends RecyclerView.ViewHolder{
        TextView StoreInfo;
        Button Enter;

        public CustStoreViewHolder(@NonNull View itemView){
            super(itemView);
            StoreInfo = itemView.findViewById(R.id.StoreInfo);
            Enter = itemView.findViewById(R.id.button11);

        }
    }
}

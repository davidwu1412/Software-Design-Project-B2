package com.example.fileioexample;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.example.fileioexample.store.Store;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fileioexample.R;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class StoresListAdapter extends RecyclerView.Adapter<StoresListAdapter.storesViewHolder> {



    private List<StoreObj> storeObjList = new ArrayList<>();
    private Context context;
    private OnStoreListener myOnStoreListener;

    public StoresListAdapter(Context context, List<StoreObj> storeObjList, OnStoreListener onStoreListener){
        this.context = context;
        this.storeObjList = storeObjList;
        this.myOnStoreListener = onStoreListener;

    }


    @Override
    public void onBindViewHolder(@NonNull StoresListAdapter.storesViewHolder holder, int position) {
        StoreObj storeObj = storeObjList.get(position);
        holder.storeName.setText(storeObj.getStoreName());
        holder.storeAddress.setText(storeObj.getStoreAddress());

    }



    @NonNull
    @Override
    public storesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.stores_card, parent, false);

        return new storesViewHolder(view, myOnStoreListener);

    }


    @Override
    public int getItemCount(){
        return storeObjList.size();
    }

    public class storesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView storeName, storeAddress;
        OnStoreListener onStoreListener;

        @Override
        public void onClick(View v) {
            onStoreListener.onStoreClick(getAdapterPosition());
        }

        public storesViewHolder(@NonNull View itemView, OnStoreListener onStoreListener) {
            super(itemView);

            storeName = itemView.findViewById(R.id.store_name);
            storeAddress = itemView.findViewById(R.id.address);
            this.onStoreListener = onStoreListener;




            itemView.setOnClickListener(this);
        }
    }

    //use this interface to interpret/detect the click then use onStoreClick in the activity to send
    // the position of that clicked item;
    public interface OnStoreListener{
        void onStoreClick(int position);

    }

}

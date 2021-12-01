package com.example.fileioexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.example.fileioexample.store.Store;
import com.firebase.ui.database.FirebaseRecyclerAdapter;


public class StoresListAdapter extends FirebaseRecyclerAdapter<StoreObj, StoresListAdapter.storesViewholder>{

    public StoresListAdapter(@NonNull FirebaseRecyclerOptions<StoreObj> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull storesViewholder holder, int position, @NonNull StoreObj model) {


        holder.storeName.setText(model.getStoreName());
        holder.storeAddress.setText(model.getStoreAddress());
    }


    @NonNull
    @Override
    public storesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stores_card, parent, false);
        return new StoresListAdapter.storesViewholder(view);
    }


    public class storesViewholder extends RecyclerView.ViewHolder{
        TextView storeName, storeAddress;
        public storesViewholder(@NonNull View itemView) {
            super(itemView);

            storeName = itemView.findViewById(R.id.store_name);
            storeAddress = itemView.findViewById(R.id.address);
        }
    }
}

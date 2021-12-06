package com.example.fileioexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fileioexample.account.OwnerAccount;

import java.util.ArrayList;
import java.util.List;


public class StoresListAdapter extends RecyclerView.Adapter<StoresListAdapter.storesViewHolder> {



    //private List<StoreObj> storeObjList = new ArrayList<>();
    private ArrayList<OwnerAccount> storeList = new ArrayList<OwnerAccount>();
    private Context context;
    private OnStoreListener myOnStoreListener;

    public StoresListAdapter(Context context, ArrayList<OwnerAccount> storeObjList, OnStoreListener onStoreListener){
        this.context = context;
        this.storeList = storeObjList;
        this.myOnStoreListener = onStoreListener;

    }


    @Override
    public void onBindViewHolder(@NonNull StoresListAdapter.storesViewHolder holder, int position) {
        OwnerAccount storeObj = storeList.get(position);
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
        return storeList.size();
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

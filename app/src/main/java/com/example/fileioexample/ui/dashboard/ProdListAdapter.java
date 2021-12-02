package com.example.fileioexample.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fileioexample.R;
import com.example.fileioexample.store.Order;
import com.example.fileioexample.store.Product;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProdListAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
    private Order order;
    public ProdListAdapter(String filename) {
        try{
            FileInputStream fis = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            //read some file that stores the orders
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.frame_cart;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        //holder.getView().setText(String.valueOf(product));
    }

    //@Override
    //public int getItemCount() {
        //return order.get;
    //}
}

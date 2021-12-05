package com.example.fileioexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fileioexample.store.Product;
import com.example.fileioexample.utils.CurrentUser;
import com.example.fileioexample.utils.Popup;

import java.util.ArrayList;

public class CustProdAdapter extends RecyclerView.Adapter<CustProdAdapter.CustProdViewHolder> {
    Context context;
    ArrayList<Product> availableProducts;

    public CustProdAdapter(Context context, ArrayList<Product> availableProducts){
        this.context = context;
        this.availableProducts = availableProducts;
    }

    public String productInfoString(Product product) {
        return "Product: " + product.getName() + "\n"+
                "Brand: " + product.getBrand() + "\n"+
                "Price: " + product.getPrice() + "\n";
    }

    @NonNull
    @Override
    public CustProdAdapter.CustProdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cust_prod_layout,parent,false);
        return new CustProdAdapter.CustProdViewHolder(v, (AppCompatActivity) context);
    }

    @Override
    public void onBindViewHolder(@NonNull CustProdAdapter.CustProdViewHolder holder, int position) {
        Product p = availableProducts.get(position);
        holder.ProdInfo.setText(productInfoString(p));
        holder.PlaceOrder.setText("Add to Cart");
    }

    @Override
    public int getItemCount() {
        return availableProducts.size();
    }

    public static class CustProdViewHolder extends RecyclerView.ViewHolder{
        TextView ProdInfo;
        Button PlaceOrder;
        public CustProdViewHolder(@NonNull View itemView, AppCompatActivity activity){
            super(itemView);
            ProdInfo = itemView.findViewById(R.id.CustProdInfo);
            PlaceOrder = itemView.findViewById(R.id.Add);

            PlaceOrder.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Product currentProd = CurrentUser.store.getAvailableProducts().get(getAdapterPosition());
                    Popup.createOrderQuantityPopup(currentProd, activity);
                }
            });
        }
    }
}
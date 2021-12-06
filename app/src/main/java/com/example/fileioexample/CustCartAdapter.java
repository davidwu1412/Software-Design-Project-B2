package com.example.fileioexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.fileioexample.store.Product;
import com.example.fileioexample.utils.CurrentUser;
import com.example.fileioexample.utils.Popup;

import java.util.ArrayList;

public class CustCartAdapter extends RecyclerView.Adapter<CustCartAdapter.CustCartViewHolder> {
    Context context;
    ArrayList<Product> cart;

    public CustCartAdapter(Context context, ArrayList<Product> cart){
        this.context = context;
        this.cart = cart;
    }

    public String productInfoString(Product product) {
        return "Product: " + product.getName() + "\n"+
                "Brand: " + product.getBrand() + "\n"+
                "Price: " + product.getPrice() + "\n"+
                "Quantity: " + product.getQuantity() + "\n";
    }

    @NonNull
    @Override
    public CustCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cust_cart_layout,parent,false);
        return new CustCartViewHolder(v, (AppCompatActivity) context, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CustCartViewHolder holder, int position) {
        Product p = cart.get(position);
        holder.ProdInfo.setText(productInfoString(p));
        holder.editQuantity.setText("Edit Quantity");
    }

    @Override
    public int getItemCount() {
        return cart.size();
    }

    public static class CustCartViewHolder extends RecyclerView.ViewHolder{
        TextView ProdInfo;
        Button editQuantity;
        public CustCartViewHolder(@NonNull View itemView, AppCompatActivity activity, CustCartAdapter c){
            super(itemView);
            ProdInfo = itemView.findViewById(R.id.ProdInfo);
            editQuantity = itemView.findViewById(R.id.button10);

            editQuantity.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Product currentProd = CurrentUser.store.getAvailableProducts().get(getAdapterPosition());
                    Popup.updateOrderQuantityPopup(currentProd, activity, c);
                }
            });
        }
    }
}

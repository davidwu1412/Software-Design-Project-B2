package com.example.fileioexample.ui.ownerListProducts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fileioexample.R;
import com.example.fileioexample.store.Store;

public class OwnerListProductsAdapter extends RecyclerView.Adapter<OwnerListProductsAdapter.OwnerListProductsViewHolder> {

    private Context context;
    private Store store;

    public OwnerListProductsAdapter(Context context, Store store){
        this.context = context;
        this.store = store;
    }

    @NonNull
    @Override
    public OwnerListProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.owner_product_list_row, parent, false);
        return new OwnerListProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnerListProductsViewHolder holder, int position) {
        holder.productText.setText(store.getAvailableProducts().get(position).getName());
        holder.brandText.setText(store.getAvailableProducts().get(position).getBrand());
        holder.priceText.setText(Double.toString(store.getAvailableProducts().get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return store.getAvailableProducts().size();
    }

    public class OwnerListProductsViewHolder extends RecyclerView.ViewHolder{

        private TextView productText, brandText, priceText;

        public OwnerListProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            productText = itemView.findViewById(R.id.ownerListProducts_productName);
            brandText = itemView.findViewById(R.id.ownerListProducts_brand);
            priceText = itemView.findViewById(R.id.ownerListProducts_price);
        }
    }
}

package com.example.fileioexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fileioexample.store.Store;
import com.example.fileioexample.ui.ownerListProducts.OwnerListProductsAdapter;
import com.example.fileioexample.utils.CurrentUser;
import com.example.fileioexample.utils.NavigationUtils;
import com.example.fileioexample.utils.Popup;

public class OwnerListProductsActivity extends AppCompatActivity {

    private String ownerName;
    private Store store;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_orderlistproducts);

        ownerName = CurrentUser.ownerUsername;
        store = CurrentUser.store;
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        OwnerListProductsAdapter adapter = new OwnerListProductsAdapter(this, store);
        recyclerView.setAdapter(adapter);

        NavigationUtils.setupOwnerNavigationMenu(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NavigationUtils.currentActivity = this;
        //Log.i("demo", "OwnerProductListActivity resumed");
    }

    public void addNewProduct(View view){
        //Start the create owner account view
        Intent intent = new Intent(this, OwnerAddNewProductActivity.class);
        startActivityForResult(intent, 0);
    }

    // Call Back method to get the Message from other Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //Use request code to determine the outcome of the child
        //Result code of 1 indicates that an account was successfully created
        if(requestCode==0) {
            if(resultCode == 1){
                Popup.createNewAlertPopup("Product Added", this);
            }
        }
    }

}
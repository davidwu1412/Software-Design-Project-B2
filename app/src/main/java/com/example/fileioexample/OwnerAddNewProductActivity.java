package com.example.fileioexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.fileioexample.store.Product;
import com.example.fileioexample.utils.CurrentUser;
import com.example.fileioexample.utils.DatabaseUtils;
import com.example.fileioexample.utils.Popup;

public class OwnerAddNewProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_new_product);
    }

    public void addProduct(View view){
        EditText productNameText = (EditText) findViewById(R.id.ownerAddProduct_productName);
        String productName = productNameText.getText().toString();

        EditText brandText = (EditText) findViewById(R.id.ownerAddProduct_brand);
        String brand = brandText.getText().toString();

        EditText priceText = (EditText) findViewById(R.id.ownerAddProduct_price);
        String priceString = priceText.getText().toString();


        //Check if username text box is filled
        if(productName.equals("")){
            //Show a popup that tells the user to enter a username
            Popup.createNewAlertPopup("Product Name field is empty", this);
            return;
        }

        if(brand.equals("")){
            //Show a popup that tells the user to enter a username
            Popup.createNewAlertPopup("Brand field is empty", this);
            return;
        }

        if(priceString.equals("")){
            //Show a popup that tells the user to enter a username
            Popup.createNewAlertPopup("Price field is empty", this);
            return;
        }

        if(priceString.contains(" ")){
            Popup.createNewAlertPopup("Price field can't contain spaces", this);
            return;
        }

        if(!priceString.matches("[0-9]*\\.[0-9]*")){
            Popup.createNewAlertPopup("Price field must be a decimal number", this);
            return;
        }

        //Round the price to 2 decimal places
        double price = (double) (Math.round(Double.parseDouble(priceString) * 100)) / 100;

        //Check if product already exists
        for(Product p: CurrentUser.store.getAvailableProducts()){
            if(p.getName().equals(productName) && p.getBrand().equals(brand)){
                Popup.createNewAlertPopup("Product already in store", this);
                return;
            }
        }

        //Add the product to the store
        CurrentUser.store.getAvailableProducts().add(new Product(productName, brand, price));

        //Add the new account credentials to the database
        DatabaseUtils.writeAvailableProductsToDatabase(CurrentUser.username, CurrentUser.store);
        setResult(1); //Set result code to 1 to indicate account was successfully created
        finish();
    }

}
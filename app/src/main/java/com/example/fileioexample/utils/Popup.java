package com.example.fileioexample.utils;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fileioexample.CustCartAdapter;
import com.example.fileioexample.R;
import com.example.fileioexample.store.Product;

public class Popup {

    public static void createNewAlertPopup(String message, AppCompatActivity activity){

        //Setup the dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        final View alertPopupView = activity.getLayoutInflater().inflate(R.layout.alert_popup, null);

        TextView alertMessageTextView = (TextView) alertPopupView.findViewById(R.id.textView13);
        Button alertCancelButton = (Button) alertPopupView.findViewById(R.id.button2);

        alertMessageTextView.setText(message);

        //Display the alert dialog
        dialogBuilder.setView(alertPopupView);
        AlertDialog dialog = dialogBuilder.create();

        dialog.show();

        //Setup the button functionality to dismiss the dialog
        alertCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    public static void createOrderQuantityPopup(Product product, AppCompatActivity activity){

        //Setup the dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        final View alertPopupView = activity.getLayoutInflater().inflate(R.layout.item_quantity_popup, null);

        Button itemAddButton = (Button) alertPopupView.findViewById(R.id.itemQuantity_addButton);
        Button alertCancelButton = (Button) alertPopupView.findViewById(R.id.itemQuantity_cancelButton);

        //Display the alert dialog
        dialogBuilder.setView(alertPopupView);
        AlertDialog dialog = dialogBuilder.create();

        dialog.show();

        //Setup the button functionality to dismiss the dialog
        alertCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        itemAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText quantityText = (EditText) alertPopupView.findViewById(R.id.itemQuantity_quantityText);
                String quantityString = quantityText.getText().toString();
                if(!quantityString.matches("[0-9]+")){
                    dialog.dismiss();
                    return;
                }
                int quantity = Integer.parseInt(quantityString);

                //Update the product quantity in the cart if it exists in the cart
                for(Product p : CurrentUser.cart){
                    if(product.getName().equals(p.getName()) && product.getBrand().equals(p.getBrand())){
                        p.setQuantity(p.getQuantity() + quantity);
                        dialog.dismiss();
                        return;
                    }
                }

                //Add the product to the cart if it was not already in the cart
                CurrentUser.cart.add(new Product(product.getName(), product.getBrand(), product.getPrice(), quantity));
                dialog.dismiss();
            }
        });

    }

    public static void updateOrderQuantityPopup(Product product, AppCompatActivity activity, CustCartAdapter c){

        //Setup the dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        final View alertPopupView = activity.getLayoutInflater().inflate(R.layout.item_quantity_popup, null);

        TextView titleText = (TextView) alertPopupView.findViewById(R.id.itemQuantity_title);
        titleText.setText("Set the quantity (0 removes it from cart)");
        Button itemAddButton = (Button) alertPopupView.findViewById(R.id.itemQuantity_addButton);
        itemAddButton.setText("Set Quantity");
        Button alertCancelButton = (Button) alertPopupView.findViewById(R.id.itemQuantity_cancelButton);

        //Display the alert dialog
        dialogBuilder.setView(alertPopupView);
        AlertDialog dialog = dialogBuilder.create();

        dialog.show();

        //Setup the button functionality to dismiss the dialog
        alertCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        itemAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText quantityText = (EditText) alertPopupView.findViewById(R.id.itemQuantity_quantityText);
                String quantityString = quantityText.getText().toString();
                if(!quantityString.matches("[0-9]+")){
                    dialog.dismiss();
                    return;
                }
                int quantity = Integer.parseInt(quantityString);

                //Update the product quantity in the cart if it exists in the cart
                for(Product p : CurrentUser.cart){
                    if(product.getName().equals(p.getName()) && product.getBrand().equals(p.getBrand())){
                        p.setQuantity(quantity);
                        if(p.getQuantity() == 0)
                            CurrentUser.cart.remove(p);
                        dialog.dismiss();
                        c.notifyDataSetChanged();
                        return;
                    }
                }

            }
        });

    }

}

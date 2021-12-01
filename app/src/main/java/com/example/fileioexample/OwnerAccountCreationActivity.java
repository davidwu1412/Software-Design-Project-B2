package com.example.fileioexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fileioexample.account.Account;
import com.example.fileioexample.account.CustomerAccount;
import com.example.fileioexample.account.OwnerAccount;
import com.example.fileioexample.utils.DatabaseUtils;
import com.example.fileioexample.utils.Popup;

public class OwnerAccountCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_account_creation);
    }

    public void createOwnerAccount(View view){

        //Validate username and password
        EditText usernameText = (EditText) findViewById(R.id.editTextTextPersonName7);
        String username = usernameText.getText().toString();

        EditText passwordText = (EditText) findViewById(R.id.editTextTextPassword6);
        EditText passwordTextConfirm = (EditText) findViewById(R.id.editTextTextPassword7);
        String password = passwordText.getText().toString();

        //Check if username text box is filled
        if(username.equals("")){
            //Show a popup that tells the user to enter a username
            Popup.createNewAlertPopup("Username field is empty", this);
            return;
        }

        if(username.contains(" ")){
            //Show a popup that tells the user that their username can't contain spaces
            Popup.createNewAlertPopup("Username can't contain spaces", this);
            return;
        }

        //Check if the passwords that the user entered match
        if(!password.equals(passwordTextConfirm.getText().toString())){
            //Show a popup saying that the passwords don't match
            Popup.createNewAlertPopup("Passwords don't match", this);
            return;
        }

        //Check if password text box is filled
        if(password.equals("")){
            //Show a popup that tells the user to enter a password
            Popup.createNewAlertPopup("Password field is empty", this);
            return;
        }

        //Check if the username is taken
        if(!DatabaseUtils.validateUsername(username)) {
            //Show a popup saying that the username is taken
            Popup.createNewAlertPopup("Username is taken", this);
            return;
        }

        //Validate the store details (name and address)
        EditText storeNameText = (EditText) findViewById(R.id.editTextTextPersonName6);
        String storeName = storeNameText.getText().toString().trim();
        EditText storeAddressText = (EditText) findViewById(R.id.editTextTextPersonName);
        String storeAddress = storeAddressText.getText().toString().trim();

        if(storeName.equals("")){
            //Show a popup that tells the user to enter a store name
            Popup.createNewAlertPopup("Store Name field is empty", this);
            return;
        }

        if(storeAddress.equals("")){
            //Show a popup that tells the user to enter a store address
            Popup.createNewAlertPopup("Store Address field is empty", this);
            return;
        }

        if(!DatabaseUtils.validateNewStoreAddress(storeName, storeAddress)){
            Popup.createNewAlertPopup("Store name and address is taken", this);
            return;
        }

        //Add the new account credentials to the database
        DatabaseUtils.addOwnerAccount(username, storeName, storeAddress, password);
        setResult(1); //Set result code to 1 to indicate account was successfully created
        finish();
    }

}
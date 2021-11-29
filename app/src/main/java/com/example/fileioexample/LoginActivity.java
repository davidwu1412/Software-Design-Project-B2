package com.example.fileioexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.fileioexample.utils.DatabaseManager;
import com.example.fileioexample.utils.Popup;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Setup the database listeners
        DatabaseManager.init();
    }

    // these are for jumping into new activities after login or register
    public void StoreRegister(View view){
        //not updated
    }
    public void StoreLogin(View view){
        //not updated
    }

    public void createCustomerAccount(View view){
        //Start the create customer account view
        Intent intent = new Intent(this, CustomerAccountCreationActivity.class);
        startActivityForResult(intent, 0);
    }

    public void accountCreated(){
        Popup.createNewAlertPopup("Account successfully created", this);
    }

    public void createOwnerAccount(View view){
        //Start the create owner account view
        Intent intent = new Intent(this, OwnerAccountCreationActivity.class);
        startActivityForResult(intent, 0);
    }

    public void customerLogin(View view){

        //Get the username and password credentials entered by the user
        EditText usernameText = (EditText) findViewById(R.id.editTextTextPersonName5);
        String username = usernameText.getText().toString();
        EditText passwordText = (EditText) findViewById(R.id.editTextTextPassword5);
        String password = passwordText.getText().toString();

        if(!DatabaseManager.validateLogin(username, password)){
            //Show a popup saying that credentials are incorrect (invalid username or password)
            Popup.createNewAlertPopup("Invalid username or password", this);
            return;
        }

        Intent intent = new Intent(this,ListProd.class);
        startActivity(intent);
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
                accountCreated();
            }
        }
    }

}
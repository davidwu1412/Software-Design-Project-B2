package com.example.fileioexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fileioexample.account.CustomerAccount;
import com.example.fileioexample.account.OwnerAccount;
import com.example.fileioexample.utils.DatabaseUtils;
import com.example.fileioexample.utils.Popup;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerAccountCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account_creation);
    }

    public void createCustomerAccount(View view){
        EditText usernameText = (EditText) findViewById(R.id.editTextTextPersonName3);
        String username = usernameText.getText().toString();

        EditText passwordText = (EditText) findViewById(R.id.editTextTextPassword);
        EditText passwordTextConfirm = (EditText) findViewById(R.id.editTextTextPassword2);
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

        //Add the new account credentials to the database
        DatabaseUtils.addCustomerAccount(username, password);
        setResult(1); //Set result code to 1 to indicate account was successfully created
        finish();
    }

}
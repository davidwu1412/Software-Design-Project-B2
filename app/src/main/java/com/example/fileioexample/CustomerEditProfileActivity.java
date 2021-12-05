package com.example.fileioexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fileioexample.account.CustomerAccount;
import com.example.fileioexample.utils.CurrentUser;
import com.example.fileioexample.utils.DatabaseUtils;
import com.example.fileioexample.utils.Popup;

public class CustomerEditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_edit_profile);
        setupUsernameText();
    }

    private void setupUsernameText(){
        TextView usernameText = (TextView) findViewById(R.id.customerEditProfile_username);
        usernameText.setText(CurrentUser.ownerUsername);
    }

    //Save the changes to the profile using values entered by the user
    public void updateCustomerAccount(View view){
        EditText firstNameText = (EditText) findViewById(R.id.customerEditProfile_firstName);
        String firstName = firstNameText.getText().toString().trim();

        EditText lastNameText = (EditText) findViewById(R.id.customerEditProfile_lastName);
        String lastName = lastNameText.getText().toString().trim();

        EditText emailText = (EditText) findViewById(R.id.customerEditProfile_email);
        String email = emailText.getText().toString().trim();

        if(email.contains(" ")){
            Popup.createNewAlertPopup("Email address can't contain spaces", this);
            return;
        }

        //Update the customer object
        CurrentUser.customer = new CustomerAccount(CurrentUser.ownerUsername, firstName, lastName, email);

        //Write the new account details to the database
        DatabaseUtils.updateCustomerProfile(CurrentUser.ownerUsername, firstName, lastName, email);
        setResult(1); //Set result code to 1 to indicate account was successfully created
        finish();
    }

    //Return to the profile screen
    public void dismissProfileChanges(View view){
        setResult(0); //Set result code to 0 to indicate that profile was not changed
        finish();
    }

}
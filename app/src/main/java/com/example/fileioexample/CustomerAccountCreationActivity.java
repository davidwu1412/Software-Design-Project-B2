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

    //Components for the alert messages for invalid inputs
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView alertMessageTextView;
    private Button alertCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_account_creation);

        //databaseTest(); //Test by adding entries to the database
    }

    public void createCustomerAccount(View view){
        EditText usernameText = (EditText) findViewById(R.id.editTextTextPersonName3);
        String username = usernameText.getText().toString();

        EditText passwordText = (EditText) findViewById(R.id.editTextTextPassword);
        EditText passwordTextConfirm = (EditText) findViewById(R.id.editTextTextPassword2);
        String password = passwordText.getText().toString();

        TextView errorText = (TextView) findViewById(R.id.textView);

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
        DatabaseUtils.CUSTOMER_ACCOUNTS_REF.child(username).setValue(new CustomerAccount(username));
        DatabaseUtils.PASSWORDS_REF.child(username).setValue(password);
        setResult(1); //Set result code to 1 to indicate account was successfully created
        finish();
    }

    public void createNewAlertPopup(String message, AppCompatActivity activity){

        //Setup the dialog
        dialogBuilder = new AlertDialog.Builder(activity);
        final View alertPopupView = getLayoutInflater().inflate(R.layout.alert_popup, null);

        alertMessageTextView = (TextView) alertPopupView.findViewById(R.id.textView13);
        alertCancelButton = (Button) alertPopupView.findViewById(R.id.button2);

        alertMessageTextView.setText(message);

        //Display the alert dialog
        dialogBuilder.setView(alertPopupView);
        dialog = dialogBuilder.create();

        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
        dialog.getWindow().setLayout(width, height);

        dialog.show();

        //Setup the button functionality to dismiss the dialog
        alertCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //This method is to be used for testing the ability to write to the database
    public void databaseTest(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("accounts").child("customers").child("test").setValue(new CustomerAccount("test"));
        ref.child("accounts").child("owners").child("test2").setValue(new CustomerAccount("test2"));
    }

}
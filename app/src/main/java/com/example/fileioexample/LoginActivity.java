package com.example.fileioexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.fileioexample.account.CustomerAccount;
import com.example.fileioexample.login.LoginContract;
import com.example.fileioexample.login.LoginModel;
import com.example.fileioexample.login.LoginPresenter;
import com.example.fileioexample.store.Order;
import com.example.fileioexample.store.Product;
import com.example.fileioexample.store.Store;
import com.example.fileioexample.utils.CurrentUser;
import com.example.fileioexample.utils.DatabaseUtils;
import com.example.fileioexample.utils.Popup;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    private LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Setup the presenter and model classes which sets up the database listeners
        presenter = new LoginPresenter(new LoginModel(), this);

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

    public void loginAttempt(View view){
        presenter.login();
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

    @Override
    public String getUsername() {
        EditText usernameText = (EditText) findViewById(R.id.editTextTextPersonName5);
        String username = usernameText.getText().toString();
        return username;
    }

    @Override
    public String getPassword() {
        EditText passwordText = (EditText) findViewById(R.id.editTextTextPassword5);
        String password = passwordText.getText().toString();
        return password;
    }

    @Override
    public void displayMessage(String message) {
        Popup.createNewAlertPopup(message, this);
    }

    @Override
    public void customerLogin() {
        //Start the first customer activity
        CurrentUser.customerUsername = getUsername();
        readCustomerAccountInfo();
        Intent intent = new Intent(this,ListStores.class);
        startActivity(intent);
    }

    @Override
    public void ownerLogin() {
        //Start the first owner activity
        CurrentUser.ownerUsername = getUsername();
        DatabaseUtils.setupCurrentStore();
        //Intent intent = new Intent(this,OwnerProfileActivity.class); //Test profile screen
        Intent intent = new Intent(this,OwnerListProductsActivity.class);
        startActivity(intent);
    }

    //Read the customer account info and store is to the customer field in the CurrentUser class
    private void readCustomerAccountInfo(){

        //Read the customer data when the activity is started
        DatabaseUtils.CUSTOMER_ACCOUNTS_REF.child(CurrentUser.customerUsername).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("demo", "Error getting data", task.getException());
                }
                else {
                    if(task.getResult().getValue() != null) {
                        CurrentUser.customer = task.getResult().getValue(CustomerAccount.class);
                        Log.i("demo", "CurrentUser.customer = " + CurrentUser.customer.toString());
                    } else {
                        CurrentUser.customer = new CustomerAccount(CurrentUser.customerUsername);
                    }
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText usernameText = (EditText) findViewById(R.id.editTextTextPersonName5);
        usernameText.setText("");
        EditText passwordText = (EditText) findViewById(R.id.editTextTextPassword5);
        passwordText.setText("");
    }

}
package com.example.fileioexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.fileioexample.account.CustomerAccount;
import com.example.fileioexample.account.OwnerAccount;
import com.example.fileioexample.login.LoginContract;
import com.example.fileioexample.login.LoginModel;
import com.example.fileioexample.login.LoginPresenter;
import com.example.fileioexample.utils.CurrentUser;
import com.example.fileioexample.utils.Popup;
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

        //Test database
        //databaseTest();
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
        Intent intent = new Intent(this,ListProd.class);
        startActivity(intent);
    }

    @Override
    public void ownerLogin() {
        //Start the first owner activity
        CurrentUser.username = getUsername();
        Intent intent = new Intent(this,OwnerListProductsActivity.class);
        startActivity(intent);
    }

    //This method is to be used for testing the ability to write to the database
    public void databaseTest(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("accounts").child("customers").child("test").setValue(new CustomerAccount("test"));
        OwnerAccount owner = new OwnerAccount("test2", "storeName", "address");
        ref.child("accounts").child("owners").child("test2").setValue(owner);
    }

}
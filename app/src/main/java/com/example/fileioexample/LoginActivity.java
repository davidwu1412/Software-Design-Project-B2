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
        CurrentUser.username = getUsername();
        readCustomerAccountInfo();
        Intent intent = new Intent(this,CustomerProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void ownerLogin() {
        //Start the first owner activity
        CurrentUser.username = getUsername();
        DatabaseUtils.setupCurrentStore();
        //Intent intent = new Intent(this,OwnerProfileActivity.class); //Test profile screen
        Intent intent = new Intent(this,OwnerListProductsActivity.class);
        startActivity(intent);
    }

    //Read the customer account info and store is to the customer field in the CurrentUser class
    private void readCustomerAccountInfo(){

        //Read the customer data when the activity is started
        DatabaseUtils.CUSTOMER_ACCOUNTS_REF.child(CurrentUser.username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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
                        CurrentUser.customer = new CustomerAccount(CurrentUser.username);
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

    //This method is to be used for testing the ability to write to the database
    public void databaseTest(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        /*ref.child("accounts").child("customers").child("test").setValue(new CustomerAccount("test"));
        OwnerAccount owner = new OwnerAccount("test2", "storeName", "address");
        ref.child("accounts").child("owners").child("test2").setValue(owner);*/
        Store store = new Store("storeName", "address2");
        store.getAvailableProducts().add(new Product("chips", "lays", 0.99));
        store.getAvailableProducts().add(new Product("cola", "pepsi", 1.99));
        Order order1 = new Order();
        order1.setCustomerUsername("test3");
        order1.setOrderId(1);
        order1.setFulfilled(false);
        order1.getProducts().add(new Product("cola", "pepsi", 1.99, 2));
        order1.getProducts().add(new Product("chips", "lays", 0.99, 3));
        order1.updateTotalCost();
        store.getOrdersList().add(order1);
        //ref.child("stores").child("test4").setValue(store);
        DatabaseUtils.writeStoreToDatabase("test4", store);
    }

}
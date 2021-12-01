package com.example.fileioexample.login;

import android.content.Intent;

import com.example.fileioexample.ListProd;

public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.Model model;
    private LoginContract.View view;

    public LoginPresenter(LoginContract.Model model, LoginContract.View view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void login() {

        String username = view.getUsername();
        String password = view.getPassword();

        //Check if the account credentials exist
        if(model.validateLogin(username, password)){
            //Check if the user is valid and if it's a customer or owner account
            if(model.accountType(username).equals("customer")){
                view.customerLogin();
            }else if(model.accountType(username).equals("owner")){
                view.ownerLogin();
            }else{
                //This should only occur if the password data exists but
                //the user isn't stored in the account types
                view.displayMessage("Unable to identify");
            }
        }else{
            view.displayMessage("Invalid username or password");
        }

    }

}

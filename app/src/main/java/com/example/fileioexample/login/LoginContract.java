package com.example.fileioexample.login;

import android.view.View;

public interface LoginContract {

    public interface Model{
        public String accountType(String username);
        public boolean validateLogin(String username, String password);
    }

    public interface View{
        public String getUsername();
        public String getPassword();

        //Message displayed should be decided by presenter
        public void displayMessage(String message);

        //Login to a customer or owner account
        public void customerLogin();
        public void ownerLogin();
    }

    public interface Presenter{
        //Use model to check if the username and password are correct and display the appropriate
        //error message to view or advance to the next activity if login is successful
        public void login();
    }

}

package com.example.fileioexample.account;

import java.util.Objects;

public class Account {

    private String username;

    public Account() {
    }

    public Account(String username) {
        this.username = username;
    }

    public Account(String username, String password) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return username.equals(account.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                '}';
    }
}

package com.example.fileioexample;

public class Product{
    private static int prodId = 0;
    int id;
    String name;
    //some other fields
    private Product(){
        prodId+=1;

    }

    public static boolean check_valid(){
        return true;
    }

    public static Product create(String name){
        return null;
    }


}
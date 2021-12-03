package com.example.fileioexample;

public class CustStoreProd {
    private String brand;
    private String name;
    private double price;

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product name: '" + name + '\'' + "\n"+
                " brand: '" + brand + '\'' + "\n"+
                " price: " + price + "\n";
    }
}

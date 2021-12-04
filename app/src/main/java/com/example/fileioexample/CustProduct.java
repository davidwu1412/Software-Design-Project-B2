package com.example.fileioexample;

public class CustProduct {

    private String brand;
    private String name;
    private String price;
    private String quantity;

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public String getQuantity()
    {
        return quantity;
    }
    @Override
    public String toString() {
        return "Product name: '" + name + '\'' + "\n"+
                " brand: '" + brand + '\'' + "\n"+
                " price: " + price + "\n"+
                " quantity: " + quantity + "\n";
    }
}

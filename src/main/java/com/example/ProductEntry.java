package com.example;

public class ProductEntry {
    Product product;
    int amount;

    public ProductEntry(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public ProductEntry(String name, double price, int amount) {
        this.product = new Product(name, price);
        this.amount = amount;
    }
}

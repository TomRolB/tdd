package com.example;

import java.util.*;

public class Cart {
    HashMap<Product, Integer> products = new HashMap<>();

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void addProduct(Product aProduct) {
        addProduct(aProduct, 1);
    }

    public void addProduct(Product aProduct, int amount) {
        products.merge(aProduct, amount, Integer::sum);
    }

    public void addAllProducts(Product... products) {
        Arrays.stream(products).forEach(this::addProduct);
    }

    public void addAllProductEntries(ProductEntry... productEntries) {
        Arrays.stream(productEntries).forEach(entry -> addProduct(entry.product, entry.amount));
    }

    public boolean contains(Product aProduct) {
        return products.containsKey(aProduct);
    }

    public boolean remove(Product aProduct) {
        Integer amount = products.remove(aProduct);
        return amount != null;
    }

    public double getTotalPrice() {
        return products.entrySet().stream()
                .mapToDouble(Cart::getTotalPriceOfEntry)
                .sum()
        ;
    }

    private static double getTotalPriceOfEntry(Map.Entry<Product, Integer> entry) {
        double price = entry.getKey().price();
        Integer amount = entry.getValue();
        return round(price * amount, 2);
    }

    public static double round(double value, int places) {
        double factor = Math.pow(10, places);
        return Math.round(value * factor) / factor;
    }

    public void clear() {
        products.clear();
    }
}

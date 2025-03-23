package com.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Arrays.stream(productEntries).forEach(entry -> addProduct(entry.product(), entry.amount()));
    }

    public boolean contains(Product aProduct) {
        return products.containsKey(aProduct);
    }

    public boolean remove(Product aProduct) {
        Integer amount = products.remove(aProduct);
        return amount != null;
    }

    public Optional<Price> getTotalPrice(SingleCurrencyCatalog catalog) {
        List<ProductEntry> productEntries = products.entrySet().stream()
                .map(entry -> new ProductEntry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return catalog.getTotalPrice(productEntries);
    }

    public void clear() {
        products.clear();
    }
}

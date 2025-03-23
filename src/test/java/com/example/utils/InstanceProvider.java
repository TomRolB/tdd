package com.example.utils;

import com.example.Product;
import com.example.ProductEntry;
import com.example.SingleCurrencyCatalog;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class InstanceProvider {
    public static final Product aProduct = new Product("Apple");
    public static final Product anotherProduct = new Product("Orange");
    public static final Product yetAnotherProduct = new Product("Pineapple");
    public static final Product nonCataloguedProduct = new Product("Berries");

    public static final ProductEntry aProductEntry = new ProductEntry(aProduct, 4);
    public static final ProductEntry anotherProductEntry = new ProductEntry(anotherProduct, 2);
    public static final ProductEntry yetAnotherProductEntry = new ProductEntry(yetAnotherProduct, 1);
    public static final ProductEntry nonCataloguedProductEntry = new ProductEntry(nonCataloguedProduct, 1);


    public static final Map<Product, Double> priceMap = Map.of(
        aProduct, 12.0,
        anotherProduct, 18.0,
        yetAnotherProduct, 2.0
    );
    public static final Map<Product, Double> priceMapWithCents = Map.of(
        aProduct, 2.2,
        anotherProduct, 3.5,
        yetAnotherProduct, 4.8
    );

    public static final SingleCurrencyCatalog priceMapCatalog = new SingleCurrencyCatalog(
        Date.from(Instant.now()),
        priceMap,
        "ARS"
    );

    public static final SingleCurrencyCatalog priceMapWithCentsCatalog = new SingleCurrencyCatalog(
        Date.from(Instant.now()),
        priceMapWithCents,
        "ARS"
    );
}

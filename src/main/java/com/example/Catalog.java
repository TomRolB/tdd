package com.example;

import java.util.Date;
import java.util.Map;

public class Catalog {
    private final Date date;
    private final Map<Product, Integer> priceMap;

    public Catalog(Date date, Map<Product, Integer> priceMap) {
        if (priceMap.isEmpty()) {
            throw new IllegalArgumentException("Price map cannot be empty");
        }

        this.date = date;
        this.priceMap = priceMap;
    }

    public boolean isEmpty() {
        return priceMap.isEmpty();
    }
}

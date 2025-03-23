package com.example;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SingleCurrencyCatalog implements Catalog {
    private final Date date; // TODO: Purpose? Is it for offers?
    private final Map<Product, Double> priceMap;
    private final String currency;

    public SingleCurrencyCatalog(Date date, Map<Product, Double> priceMap, String currency) {
        this.currency = currency;
        if (priceMap.isEmpty()) {
            throw new IllegalArgumentException("Price map cannot be empty");
        }

        this.date = date;
        this.priceMap = priceMap;
    }

    public boolean isEmpty() {
        return priceMap.isEmpty();
    }

    public Optional<Price> getPrice(Product product) {
        Double priceValueOrNull = priceMap.get(product);

        if (priceValueOrNull == null) return Optional.empty();
        return Optional.of(new Price(this.currency, priceValueOrNull));
    }

    public Optional<Price> getTotalPrice(List<ProductEntry> productEntries) {
        if (productEntries.isEmpty()) return Optional.of(new Price(currency, 0));

        Price totalPrice = Price.zero();
        for (ProductEntry entry : productEntries) {
            Product product = entry.product();
            Optional<Price> priceOptional = this.getPrice(product);

            if (priceOptional.isEmpty()) return Optional.empty();
            Price totalProductPrice = priceOptional.get().multiply(entry.amount());
            totalPrice = totalProductPrice.add(totalPrice);
        }

        return Optional.of(totalPrice);
    }
}

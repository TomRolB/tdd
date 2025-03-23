package com.example;

import java.util.List;
import java.util.Optional;

public interface Catalog {
    boolean isEmpty();
    Optional<Price> getPrice(Product product);
    Optional<Price> getTotalPrice(List<ProductEntry> productEntries);
}

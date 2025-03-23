package com.example;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    Product aProduct = new Product("Apple");
    Product anotherProduct = new Product("Orange");
    Product yetAnotherProduct = new Product("Pineapple");

    Map<Product, Double> priceMap = Map.of(
        aProduct, 12.0,
        anotherProduct, 18.0,
        yetAnotherProduct, 2.0
    );
    Map<Product, Double> priceMapWithCents = Map.of(
        aProduct, 2.2,
        anotherProduct, 3.5,
        yetAnotherProduct, 4.8
    );

    @Test
    public void CreateCardShouldBeEmpty_001() {
        Cart aCart = new Cart();

        assertTrue(aCart.isEmpty());
    }


    @Test
    public void AddingProductToCartShouldMakeItContainTheProduct_002() {
        Cart aCart = new Cart();

        Product aProduct = new Product("apple");
        aCart.addProduct(aProduct);

        assertFalse(aCart.isEmpty());
        assertTrue(aCart.contains(aProduct));
    }

    @Test
    public void RemovingAddedProductShouldLeaveCartEmpty_003() {
        Cart aCart = new Cart();

        Product aProduct = new Product("apple");
        aCart.addProduct(aProduct);
        assertFalse(aCart.isEmpty());

        boolean isRemoved = aCart.remove(aProduct);
        assertTrue(aCart.isEmpty());
        assertTrue(isRemoved);
    }

    @Test
    public void RemovingNonExistentProductShouldDoNothing_004() {
        Cart aCart = new Cart();

        Product aProduct = new Product("apple");
        assertTrue(aCart.isEmpty());

        boolean isRemoved = aCart.remove(aProduct);
        assertTrue(aCart.isEmpty());
        assertFalse(isRemoved);
    }

    @Test
    public void ShouldAddAllProductsToCart_005() {
        Cart aCart = new Cart();

        Product aProduct = new Product("Apple");
        Product anotherProduct = new Product("Orange");
        Product yetAnotherProduct = new Product("Pineapple");
        List<Product> products = List.of(aProduct, anotherProduct, yetAnotherProduct);

        aCart.addAllProducts(aProduct, anotherProduct, yetAnotherProduct);

        products.forEach(product -> assertTrue(aCart.contains(product)));
    }
    @Test
    public void TotalPriceShouldBeTheAdditionOfTheProductsPrices_006() {
        Cart aCart = new Cart();
        SingleCurrencyCatalog catalog = new SingleCurrencyCatalog(Date.from(Instant.now()), priceMap, "ARS");

        aCart.addAllProducts(aProduct, anotherProduct, yetAnotherProduct);

        final Optional<Price> totalPriceOptional = aCart.getTotalPrice(catalog);

        assertTrue(totalPriceOptional.isPresent());
        assertEquals(new Price("ARS", 32), totalPriceOptional.get());
    }

    @Test
    public void TotalPriceShouldBeTheAdditionOfTheProductsPricesConsideringAmounts_007() {
        Cart aCart = new Cart();

        ProductEntry aProductEntry = new ProductEntry(aProduct, 4);
        ProductEntry anotherProductEntry = new ProductEntry(anotherProduct, 2);
        ProductEntry yetAnotherProductEntry = new ProductEntry(yetAnotherProduct, 1);

        aCart.addAllProductEntries(aProductEntry, anotherProductEntry, yetAnotherProductEntry);

        SingleCurrencyCatalog catalog = new SingleCurrencyCatalog(
            Date.from(Instant.now()),
            priceMapWithCents,
            "ARS"
        );

        final Optional<Price> totalPrice = aCart.getTotalPrice(catalog);

        assertTrue(totalPrice.isPresent());
        assertEquals(new Price("ARS", 20.6), totalPrice.get());
    }

    @Test
    public void TotalPriceShouldBeZeroIfCartIsEmpty_008() {
        Cart aCart = new Cart();
        SingleCurrencyCatalog catalog = new SingleCurrencyCatalog(
            Date.from(Instant.now()),
            priceMapWithCents,
            "ARS"
        );
        Optional<Price> totalPrice = aCart.getTotalPrice(catalog);

        assertTrue(totalPrice.isPresent());
        assertEquals(new Price("ARS", 0), totalPrice.get());
    }

    @Test
    public void CartShouldBeEmptyWhenCleared_009() {
        Cart aCart = new Cart();

        Product aProduct = new Product("Apple");
        aCart.addProduct(aProduct);
        aCart.clear();

        assertTrue(aCart.isEmpty());
    }
}


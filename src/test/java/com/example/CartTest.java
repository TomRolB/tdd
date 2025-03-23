package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.utils.InstanceProvider.*;
import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    private Cart aCart;

    @BeforeEach
    public void setUp() {
        aCart = new Cart();
    }

    @Test
    public void CreateCardShouldBeEmpty_001() {
        assertTrue(aCart.isEmpty());
    }


    @Test
    public void AddingProductToCartShouldMakeItContainTheProduct_002() {
        aCart.addProduct(aProduct);

        assertFalse(aCart.isEmpty());
        assertTrue(aCart.contains(aProduct));
    }

    @Test
    public void RemovingAddedProductShouldLeaveCartEmpty_003() {
        aCart.addProduct(aProduct);
        assertFalse(aCart.isEmpty());

        boolean isRemoved = aCart.remove(aProduct);
        assertTrue(aCart.isEmpty());
        assertTrue(isRemoved);
    }

    @Test
    public void RemovingNonExistentProductShouldDoNothing_004() {
        assertTrue(aCart.isEmpty());

        boolean isRemoved = aCart.remove(aProduct);
        assertTrue(aCart.isEmpty());
        assertFalse(isRemoved);
    }

    @Test
    public void ShouldAddAllProductsToCart_005() {
        List<Product> products = List.of(aProduct, anotherProduct, yetAnotherProduct);
        aCart.addAllProducts(aProduct, anotherProduct, yetAnotherProduct);

        products.forEach(product -> assertTrue(aCart.contains(product)));
    }
    @Test
    public void TotalPriceShouldBeTheAdditionOfTheProductsPrices_006() {
        aCart.addAllProducts(aProduct, anotherProduct, yetAnotherProduct);

        final Optional<Price> totalPriceOptional = aCart.getTotalPrice(priceMapCatalog);

        assertTrue(totalPriceOptional.isPresent());
        assertEquals(new Price("ARS", 32), totalPriceOptional.get());
    }

    @Test
    public void TotalPriceShouldBeTheAdditionOfTheProductsPricesConsideringAmounts_007() {
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
        Optional<Price> totalPrice = aCart.getTotalPrice(priceMapWithCentsCatalog);

        assertTrue(totalPrice.isPresent());
        assertEquals(new Price("ARS", 0), totalPrice.get());
    }

    @Test
    public void CartShouldBeEmptyWhenCleared_009() {
        aCart.addProduct(aProduct);
        aCart.clear();

        assertTrue(aCart.isEmpty());
    }

    @Test
    public void CartShouldListProducts_010() {
        throw new RuntimeException("Not tested yet");
    }
}


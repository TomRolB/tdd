package com.example;

import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {
    @Test
    public void CreateCardShouldBeEmpty_001() {
        Cart aCart = new Cart();

        assertTrue(aCart.isEmpty());
    }


    @Test
    public void AddingProductToCartShouldMakeItContainTheProduct_002() {
        Cart aCart = new Cart();

        Product aProduct = new Product("apple", 5);
        aCart.addProduct(aProduct);

        assertFalse(aCart.isEmpty());
        assertTrue(aCart.contains(aProduct));
    }

    @Test
    public void RemovingAddedProductShouldLeaveCartEmpty_003() {
        Cart aCart = new Cart();

        Product aProduct = new Product("apple", 5);
        aCart.addProduct(aProduct);
        assertFalse(aCart.isEmpty());

        boolean isRemoved = aCart.remove(aProduct);
        assertTrue(aCart.isEmpty());
        assertTrue(isRemoved);
    }

    @Test
    public void RemovingNonExistentProductShouldDoNothing_004() {
        Cart aCart = new Cart();

        Product aProduct = new Product("apple", 5);
        assertTrue(aCart.isEmpty());

        boolean isRemoved = aCart.remove(aProduct);
        assertTrue(aCart.isEmpty());
        assertFalse(isRemoved);
    }

    @Test
    public void ShouldAddAllProductsToCart_005() {
        Cart aCart = new Cart();

        Product aProduct = new Product("Apple", 5);
        Product anotherProduct = new Product("Orange", 10);
        Product yetAnotherProduct = new Product("Pineapple", 17);
        List<Product> products = List.of(aProduct, anotherProduct, yetAnotherProduct);

        aCart.addAllProducts(aProduct, anotherProduct, yetAnotherProduct);

        products.forEach(product -> assertTrue(aCart.contains(product)));
    }
    @Test
    public void TotalPriceShouldBeTheAdditionOfTheProductsPrices_006() {
        Cart aCart = new Cart();

        Product aProduct = new Product("Apple", 5);
        Product anotherProduct = new Product("Orange", 10);
        Product yetAnotherProduct = new Product("Pineapple", 17);

        aCart.addAllProducts(aProduct, anotherProduct, yetAnotherProduct);

        final double totalPrice = aCart.getTotalPrice();
        assertEquals(32, totalPrice);
    }

    @Test
    public void TotalPriceShouldBeTheAdditionOfTheProductsPricesConsideringAmounts_007() {
        Cart aCart = new Cart();

        ProductEntry aProductEntry = new ProductEntry("Apple", 5.2, 4);
        ProductEntry anotherProductEntry = new ProductEntry("Orange", 10.5, 2);
        ProductEntry yetAnotherProductEntry = new ProductEntry("Pineapple", 17.1, 1);

        aCart.addAllProductEntries(aProductEntry, anotherProductEntry, yetAnotherProductEntry);

        final double totalPrice = aCart.getTotalPrice();
        assertEquals(58.9, totalPrice);
    }

    @Test
    public void TotalPriceShouldBeZeroIfCartIsEmpty_008() {
        Cart aCart = new Cart();
        double totalPrice = aCart.getTotalPrice();

        assertEquals(0, totalPrice);
    }

    @Test
    public void CartShouldBeEmptyWhenCleared_009() {
        Cart aCart = new Cart();

        Product aProduct = new Product("Apple", 5);
        aCart.addProduct(aProduct);
        aCart.clear();

        assertTrue(aCart.isEmpty());
    }
}


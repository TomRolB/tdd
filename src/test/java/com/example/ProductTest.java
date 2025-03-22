package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    public void productsShouldBeEqual() {
        Product someProduct = new Product("Apple", 10);
        Product anotherProduct = new Product("Apple", 10);

        Assertions.assertEquals(someProduct, anotherProduct);
    }

    @Test void productsShouldNotBeEqual() {
        Product someProduct = new Product("Apple", 10);
        Product anotherProduct = new Product("Orange", 8);

        Assertions.assertNotEquals(someProduct, anotherProduct);
        Assertions.assertNotEquals(someProduct.hashCode(), anotherProduct.hashCode());
    }
}

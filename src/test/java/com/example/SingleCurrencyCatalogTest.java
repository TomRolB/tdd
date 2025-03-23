package com.example;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.utils.InstanceProvider.*;
import static org.junit.jupiter.api.Assertions.*;

/*
    Maneja precios de productos: crear, modificar, eliminar
 */

// Pagos: Con qué se abona. Si el pago es exitoso, se emite factura; si no, decidir qué hacer. Tener en cuenta
//  que existe un componente externo como el Posnet que puede tirar distintos códigos de error. El punto
//  es, ¿cómo metemos en nuestros test un dispositivo externo como Posnet, que no puede usarse justamente en un test?

// Manejo de ofertas (opcional): descuentos (días específicos); esto afecta a la parte de pagos
// Trueque, multi-moneda (opcional)

class SingleCurrencyCatalogTest {
    @Test
    public void CatalogStartsWithValidityDateAndPriceList_001() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new SingleCurrencyCatalog(Date.from(Instant.now()), Map.of(), "ARS")
        );
    }

    @Test
    public void CatalogKnowsProductPrice_002() {
        Optional<Price> price = priceMapCatalog.getPrice(aProduct);

        assertTrue(price.isPresent());
        assertEquals(new Price("ARS", 12.0), price.get());
    }

    @Test
    public void IfCatalogDoesNotKnowTheProductThenOptionalEmptyIsReturned_003() {
        Optional<Price> price = priceMapCatalog.getPrice(nonCataloguedProduct);

        assertFalse(price.isPresent());
    }

    @Test
    public void CatalogComputesTotalPrice_004() {
        Optional<Price> price = priceMapWithCentsCatalog.getTotalPrice(List.of(aProductEntry, anotherProductEntry));

        assertTrue(price.isPresent());
        assertEquals(new Price("ARS", 15.8), price.get());
    }

    @Test
    public void CatalogReturnsZeroPriceIfAnEmptyListIsPassed_005() {
        Optional<Price> price = priceMapWithCentsCatalog.getTotalPrice(List.of());

        assertTrue(price.isPresent());
        assertEquals(new Price("ARS", 0), price.get());
    }

    @Test
    public void CatalogReturnsOptionalEmptyIfAnyProductIsNotCatalogued_006() {
        Optional<Price> price = priceMapWithCentsCatalog.getTotalPrice(
            List.of(aProductEntry, anotherProductEntry, yetAnotherProductEntry, nonCataloguedProductEntry)
        );

        assertFalse(price.isPresent());
    }
}
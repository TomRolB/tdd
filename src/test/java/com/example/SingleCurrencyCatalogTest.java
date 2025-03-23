package com.example;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

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
    public void CatalogStartsWithValidityDateAndPriceList() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new SingleCurrencyCatalog(Date.from(Instant.now()), Map.of(), "ARS")
        );
    }

    @Test
    public void CatalogKnowsProductPrice() {

    }
    @Test
    public void IfCatalogDoesNotKnowTheProductThen() {
        // Decide what to do
    }
}
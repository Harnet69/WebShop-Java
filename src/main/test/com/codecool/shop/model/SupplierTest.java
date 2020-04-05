package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {
    private Supplier supplier;
    private ProductCategory terminator;
    private Product product;

    @BeforeEach
    void setUp() {
        supplier = new Supplier("SkyNet", "The China's producer of electronic devices");
        terminator = new ProductCategory("Terminators", "Devices for fun", "Very difficult to screw up machines");
        product = new Product("T1000", 999.00, "USD", "The best Terminator ever!", terminator, supplier);
    }

    @Test
    void testAddProduct() {
        int prodQtt = supplier.getProducts().size();
        supplier.addProduct(product);
        int prodQttAfter = supplier.getProducts().size();
        assertNotEquals(prodQtt, prodQttAfter);
    }
}
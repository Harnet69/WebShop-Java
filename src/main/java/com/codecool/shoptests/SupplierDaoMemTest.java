package com.codecool.shoptests;


import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplierDaoMemTest {
    private static SupplierDao supplierDao = SupplierDaoMem.getInstance();
    private static Supplier testSupplier = new Supplier("TestName", "TestDescription");
    private static Supplier testSupplierTwo = new Supplier("TestName2", "TestDescription2");

    //ID generating in add() methods in every Singleton is broken. Will fix in next sprint with PSQL implementation.

    private String expected = "id: 3, " +
            "name: TestName, " +
            "description: TestDescription";
    private String expectedTwo = "id: 2, " +
            "name: TestName2, " +
            "description: TestDescription2";

    @BeforeAll
    public static void setup() {
        supplierDao.add(testSupplier);
        supplierDao.add(testSupplierTwo);
    }

    @Test
    void add() {
        Supplier testAddSupplier = new Supplier("TestName", "TestDescription");
        supplierDao.add(testAddSupplier);
        assertEquals(expected, supplierDao.getAll().get(1).toString());
        assertEquals(expectedTwo, supplierDao.getAll().get(1).toString());
    }


    @Test
    void find() {
        assertEquals(expectedTwo, supplierDao.find(2).toString());
    }


    @Test
    void remove() {
        supplierDao.remove(1);
        assertEquals(expectedTwo, supplierDao.getAll().get(0).toString());
    }
}
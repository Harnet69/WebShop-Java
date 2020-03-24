package com.codecool.shoptests;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductCategoryDaoMemTest {

    ProductCategoryDao productCategoryDao;
    ProductCategory testCategory = new ProductCategory("TestName", "TestDepartment", "TestDescription");

    @BeforeEach
    void setUp() {
        productCategoryDao = ProductCategoryDaoMem.getInstance();
    }

    @Test
    void testAddOne() {
        productCategoryDao.add(testCategory);
        String expected = "id: 1," +
                "name: TestName, " +
                "department: TestDepartment, " +
                "description: TestDescription";
        assertEquals(expected, productCategoryDao.getAll().get(0).toString());
    }

    void multipleAddTests() {

    }

}
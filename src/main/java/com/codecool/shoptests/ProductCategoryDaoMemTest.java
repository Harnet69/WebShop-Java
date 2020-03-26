package com.codecool.shoptests;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductCategoryDaoMemTest {

    private static ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();
    private static ProductCategory testCategory = new ProductCategory("TestName", "TestDepartment", "TestDescription");
    private static ProductCategory testCategoryTwo = new ProductCategory("TestName2", "TestDepartment2", "TestDescription2");
    private String expected = "id: 1," +
            "name: TestName, " +
            "department: TestDepartment, " +
            "description: TestDescription";

    private String expectedTwo = "id: 2," +
            "name: TestName2, " +
            "department: TestDepartment2, " +
            "description: TestDescription2";


    @BeforeAll
    public static void setup() {
        productCategoryDao.add(testCategory);
        productCategoryDao.add(testCategoryTwo);
    }

    @Test
    void testAdd() {
        assertEquals(expected, productCategoryDao.getAll().get(0).toString());
        assertEquals(expectedTwo, productCategoryDao.getAll().get(1).toString());
    }


    @Test
    void find() {
        assertEquals(expectedTwo, productCategoryDao.find(2).toString());
    }


    @Test
    void remove() {
        productCategoryDao.remove(1);
        assertEquals(expectedTwo, productCategoryDao.getAll().get(0).toString());
    }
}
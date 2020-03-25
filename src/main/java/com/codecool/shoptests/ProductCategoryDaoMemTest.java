package com.codecool.shoptests;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductCategoryDaoMemTest {

    private ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();
    private ProductCategory testCategory = new ProductCategory("TestName", "TestDepartment", "TestDescription");
    private ProductCategory testCategoryTwo = new ProductCategory("TestName2", "TestDepartment2", "TestDescription2");
    private String expected = "id: 1," +
            "name: TestName, " +
            "department: TestDepartment, " +
            "description: TestDescription";

    private String expectedTwo = "id: 2," +
            "name: TestName2, " +
            "department: TestDepartment2, " +
            "description: TestDescription2";


    @Test
    void testAdd() {
        productCategoryDao.add(testCategory);
        productCategoryDao.add(testCategoryTwo);

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
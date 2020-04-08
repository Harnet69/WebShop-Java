package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductDao {

    private List<ProductCategory> data = new ArrayList<>();
    private static ProductCategoryDaoJdbc instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoJdbc() {
    }

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll(){
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBySupplier(String supplierName) {
        return null;
    }

    @Override
    public List<Product> getByCategory(String categoryName) {
        return null;
    }

    @Override
    public List<Product> getByCategorySupplier(String category, String supplierName) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}

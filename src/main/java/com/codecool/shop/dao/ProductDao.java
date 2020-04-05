package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {

    void add(Product product);
    Product find(int id);
    void remove(int id);


    List<Product> getAll() throws SQLException;
    List<Product> getBy(Supplier supplier);
    List<Product> getBySupplier(String supplierName);
    List<Product> getByCategory(String categoryName);
    List<Product> getByCategorySupplier(String category, String supplierName);
    List<Product> getBy(ProductCategory productCategory);

}

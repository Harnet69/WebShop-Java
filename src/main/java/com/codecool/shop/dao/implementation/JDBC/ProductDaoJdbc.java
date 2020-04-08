package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ProductDaoJdbc implements ProductDao {
    private final ProductDaoSql sql = new ProductDaoSql();
    private static ProductDaoJdbc instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoJdbc() {
    }

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

    //use for all methods
    private List<Product> getProductsFromDB(ResultSet rs){
        List<Product> data = new ArrayList<>();
        try {
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float defaultPrice = rs.getFloat("defaultPrice");
                String defaultCurrency = rs.getString("defaultCurrency");
                String productCategory = rs.getString("productCategory");
                String catDep = rs.getString("catDep");
                String catDesc = rs.getString("catDesc");
                String supplierName = rs.getString("supplierName");
                String supplDesc = rs.getString("supplDesc");

                Product product = new Product(name, defaultPrice, defaultCurrency, description,
                        new ProductCategory(productCategory, catDep, catDesc),
                        new Supplier(supplierName, supplDesc));
                product.setId(id);
                data.add(product);
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return data;
    }

    @Override
    public void add(Product product) {
    }

    @Override
    public Product find(int id) {
        try {
            return getOrderedProdsById(String.valueOf(id)).get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public List<Product> getAll() {
        try {
            ResultSet rs = sql.getAllProducts();
            return getProductsFromDB(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        try {
            return getBySupplier(supplier.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> getBySupplier(String supplierName) {
        ResultSet rs = sql.getProductsBySupplier(supplierName);
        return getProductsFromDB(rs);

    }

    @Override
    public List<Product> getByCategory(String categoryName){
        ResultSet rs = sql.getProductsByCategory(categoryName);
        return getProductsFromDB(rs);
    }

    @Override
    public List<Product> getByCategorySupplier(String categoryName, String supplierName){
        ResultSet rs = sql.getProductsByCategory(categoryName, supplierName);
        return getProductsFromDB(rs);
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory){
        return getByCategory(productCategory.getName());
    }

    public List<Product> getOrderedProdsById(String productsId){
        List<String> myList = null;
            List<Product> orderedProds = new ArrayList<>();
        try {
            myList = new ArrayList<>(Arrays.asList(productsId.split(",")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            for (String id : myList) {
                ResultSet rs = sql.getProductsById(Integer.parseInt(id));
                orderedProds.add(Objects.requireNonNull(getProductsFromDB(rs)).get(0));
            }
        } catch (NumberFormatException throwables) {
            throwables.printStackTrace();
        }
        return orderedProds;
    }
}

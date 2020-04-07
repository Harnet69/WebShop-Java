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

public class ProductDaoJdbc implements ProductDao {
    private ProductDaoSql sql = new ProductDaoSql();
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
    private List<Product> getProductsFromDB(ResultSet rs) throws SQLException {
        List<Product> data = new ArrayList<>();
        while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("id");
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
        return data;
    }

    @Override
    public void add(Product product) {
    }

    @Override
    public Product find(int id) throws SQLException {
        return getOrderedProdsById(String.valueOf(id)).get(0);
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public List<Product> getAll() throws SQLException {
        ResultSet rs = sql.getAllProducts();
        return getProductsFromDB(rs);
    }

    @Override
    public List<Product> getBy(Supplier supplier) throws SQLException {
        return getBySupplier(supplier.getName());
    }

    @Override
    public List<Product> getBySupplier(String supplierName) throws SQLException {
        ResultSet rs = sql.getProductsBySupplier(supplierName);
        return getProductsFromDB(rs);
    }

    @Override
    public List<Product> getByCategory(String categoryName) throws SQLException {
        ResultSet rs = sql.getProductsByCategory(categoryName);
        return getProductsFromDB(rs);
    }

    @Override
    public List<Product> getByCategorySupplier(String categoryName, String supplierName) throws SQLException {
        ResultSet rs = sql.getProductsByCategory(categoryName, supplierName);
        return getProductsFromDB(rs);
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) throws SQLException {
        return getByCategory(productCategory.getName());
    }

    public List<Product> getOrderedProdsById(String productsId) throws SQLException {
        List<String> myList = new ArrayList<>(Arrays.asList(productsId.split(",")));
        List<Product> orderedProds= new ArrayList<>();
        for(String id : myList){
            ResultSet rs = sql.getProductsById(Integer.parseInt(id));
            orderedProds.add(getProductsFromDB(rs).get(0));
        }
        return orderedProds;
    }
}

package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.connect.JDBC;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
//    private List<Product> data = new ArrayList<>();
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
    public Product find(int id){
        return null;
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
//        ResultSet rs = sql.getProductsBySupplier(supplier);
//        return getProductsFromDB(rs);
        return null;
    }

    @Override
    public List<Product> getBySupplier(String supplierName) throws SQLException {
        ResultSet rs = sql.getProductsBySupplier(supplierName);
        return getProductsFromDB(rs);
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

package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.connect.JDBC;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {

    private List<Product> data = new ArrayList<>();
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
    public List<Product> getAll() throws SQLException {
        List<Product> allProducts = new ArrayList<>();

        DataSource dataSource = JDBC.connect();
        Statement stmt = dataSource.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM product");
//        List<Author> authorsFromDb = new ArrayList<>();

        while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("id");
            String name = rs.getString("name");
            String image = rs.getString("image");
            String description = rs.getString("description");
            // TODO add Product from database to Array and pass it to Product Controller

            //            allProducts.add(new Product())
            System.out.println(id + name + image);
//            authorsFromDb.add(new Author(id, first, last, birth));
        }
        rs.close();

//        return authorsFromDb;
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

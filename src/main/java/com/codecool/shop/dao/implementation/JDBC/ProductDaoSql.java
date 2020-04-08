package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.connect.JDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDaoSql {
    private final String baseSqlString = "SELECT p.id as id, p.name as name, p.description as description, p.default_price as defaultPrice, p.default_currency as defaultCurrency,\n" +
            "    c.name as productCategory, c.department as catDep, c.description as catDesc,\n" +
            "        s.name as supplierName, s.description as supplDesc  FROM product as p\n" +
            "LEFT JOIN category c on p.product_cat = c.id\n" +
            "LEFT JOIN supplier s on p.supplier = s.id ";

    public ResultSet getAllProducts() throws SQLException {
        Statement stmt = null;
        try{
        JDBC.openConnection();
        stmt = JDBC.openConnection().createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert stmt != null;
        return stmt.executeQuery(baseSqlString);
    }

    public ResultSet getProductsBySupplier(String supplierName){
        try {
            PreparedStatement statement = JDBC.openConnection().prepareStatement(baseSqlString + " WHERE s.name = ?");
            statement.setString(1, supplierName);
            return statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public ResultSet getProductsByCategory(String categoryName){
        try {
            PreparedStatement statement = JDBC.openConnection().prepareStatement(baseSqlString + " WHERE c.name = ?");
            statement.setString(1, categoryName);
            return statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public ResultSet getProductsByCategory(String categoryName,String supplierName){
        try {
            PreparedStatement statement = JDBC.openConnection().prepareStatement(baseSqlString + " WHERE c.name = ? AND s.name = ?");
            statement.setString(1, categoryName);
            statement.setString(2, supplierName);
            return statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public ResultSet getProductsById(int productsId){
        try {
            PreparedStatement statement = JDBC.openConnection().prepareStatement(baseSqlString + " WHERE p.id = ?");
            statement.setInt(1, productsId);
            return statement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}

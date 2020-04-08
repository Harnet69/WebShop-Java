package com.codecool.shop.connect;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.postgresql.ds.PGSimpleDataSource;

public class JDBC {

    // connect to DB using connection.properties file
    public static Connection openConnection() {

        Properties properties = new Properties();
        Connection connection = null;

        String path = System.getProperty("user.dir");

        path += "/src/main/resources/database.properties";

        try(FileInputStream fin = new FileInputStream(path);) {

            properties.load(fin);

            try {
                Class.forName(properties.getProperty("JDBC_DRIVER"));

//              opening connection
                connection = (Connection) DriverManager.getConnection(properties.getProperty("DB_URL"),properties.getProperty("USER"),properties.getProperty("PASS"));

            } catch (ClassNotFoundException e) {
                System.out.println("This is from openConnection method");
                e.printStackTrace();
            } catch (SQLException f) {
                System.out.println("This is from openConnection method");
                f.printStackTrace();
            }
        } catch (IOException io) {
            System.out.println("This is from openConnection method");
            io.printStackTrace();
        }
        return connection;
    }



//    public static DataSource connect() throws SQLException {
//        PGSimpleDataSource dataSource = new PGSimpleDataSource();
//
//        dataSource.setDatabaseName("codecoolshop");
//        dataSource.setUser("harnet");
//        dataSource.setPassword("1234");
//
////        System.out.println("Trying to connect...");
//        dataSource.getConnection().close();
////        System.out.println("Connection OK");
//
//        return dataSource;
//    }
}

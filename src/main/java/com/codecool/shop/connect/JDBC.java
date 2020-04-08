package com.codecool.shop.connect;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC {

    // connect to DB using database.properties file
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

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("This is from openConnection method");
                e.printStackTrace();
            }
        } catch (IOException io) {
            System.out.println("This is from openConnection method");
            io.printStackTrace();
        }
        return connection;
    }
}

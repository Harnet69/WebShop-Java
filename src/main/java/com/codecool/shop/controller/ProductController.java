package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.JDBC.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.JDBC.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        //work with data store
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        // work with database
        ProductDaoJdbc productJdbc = ProductDaoJdbc.getInstance();
        ProductCategoryDaoJdbc productCategoryDataJdbc = ProductCategoryDaoJdbc.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

//        try {
//            jdbc.getAll();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        CatController cat = new CatController();
        try {
            cat.filterProducts(productDataStore, productCategoryDataStore, engine, context, req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

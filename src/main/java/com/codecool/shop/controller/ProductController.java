package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.JDBC.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
        Logger log = LoggerFactory.getLogger(ProductController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        //SLF4J
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warning");
        log.error("error");

        //work with data store
        ProductDao productDataStore = ProductDaoMem.getInstance();
        // work with database
        ProductDaoJdbc productJdbc = ProductDaoJdbc.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        try {
            // you can switch between products in memory and from database
            CatController.filterProducts(productJdbc, engine, context, req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database wasn't found!");
        }
    }
}

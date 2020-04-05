package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

// make class generic ???
public class CatController {
    public void filterProducts (ProductDao productDataStore, ProductCategoryDao productCategoryDataStore,
                                TemplateEngine engine, WebContext context, HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        if (req.getParameter("category") == null && req.getParameter("supplier") == null) {
            context.setVariable("products", productDataStore.getAll());
            engine.process("product/index.html", context, resp.getWriter());
        } else if (req.getParameter("category").equals("all") && !req.getParameter("supplier").equals("all")) {
            context.setVariable("products", productDataStore.getBySupplier(req.getParameter("supplier")));
            engine.process("product/products.html", context, resp.getWriter());
        } else if (!req.getParameter("category").equals("all") && req.getParameter("supplier").equals("all")) {
            context.setVariable("products", productDataStore.getByCategory(req.getParameter("category")));
            engine.process("product/products.html", context, resp.getWriter());
        } else if (req.getParameter("category").equals("all") && req.getParameter("supplier").equals("all")) {
            context.setVariable("products", productDataStore.getAll());
            engine.process("product/products.html", context, resp.getWriter());
        } else if (req.getParameter("category") != null && req.getParameter("supplier") != null) {
            context.setVariable("products", productDataStore.getByCategorySupplier(req.getParameter("category"), req.getParameter("supplier")));
            engine.process("product/products.html", context, resp.getWriter());
        }
    }
}

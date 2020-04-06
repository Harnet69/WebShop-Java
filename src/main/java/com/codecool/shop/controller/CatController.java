package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class CatController {
    public static void filterProducts (ProductDao productData, TemplateEngine engine, WebContext context, HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        if (req.getParameter("category") == null && req.getParameter("supplier") == null) {
            context.setVariable("products", productData.getAll());
            System.out.println(productData.getAll());
            engine.process("product/index.html", context, resp.getWriter());
        } else if (req.getParameter("category").equals("all") && !req.getParameter("supplier").equals("all")) {
            context.setVariable("products", productData.getBySupplier(req.getParameter("supplier")));
            engine.process("product/products.html", context, resp.getWriter());
        } else if (!req.getParameter("category").equals("all") && req.getParameter("supplier").equals("all")) {
            context.setVariable("products", productData.getByCategory(req.getParameter("category")));
            engine.process("product/products.html", context, resp.getWriter());
        } else if (req.getParameter("category").equals("all") && req.getParameter("supplier").equals("all")) {
            context.setVariable("products", productData.getAll());
            engine.process("product/products.html", context, resp.getWriter());
        } else if (req.getParameter("category") != null && req.getParameter("supplier") != null) {
            context.setVariable("products", productData.getByCategorySupplier(req.getParameter("category"), req.getParameter("supplier")));
            engine.process("product/products.html", context, resp.getWriter());
        }
    }
}

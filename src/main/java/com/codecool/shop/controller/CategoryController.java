package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CategoryController {
    private ProductDao productDataStore;
    private ProductCategoryDao productCategoryDataStore;
    private TemplateEngine engine;
    private WebContext context;
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public CategoryController(ProductDao productDataStore, ProductCategoryDao productCategoryDataStore,
                              TemplateEngine engine, WebContext context, HttpServletRequest req, HttpServletResponse resp) {
        this.productDataStore = productDataStore;
        this.productCategoryDataStore = productCategoryDataStore;
        this.engine = engine;
        this.context = context;
        this.req = req;
        this.resp = resp;
    }

    public void showProductsByGivingCat() throws IOException {
        if (req.getParameter("category") == null) {
            context.setVariable("products", productDataStore.getAll());
            engine.process("product/index.html", context, resp.getWriter());}
        else {
            switch (req.getParameter("category")) {
                case "tablet":
                    context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
                    engine.process("product/products.html", context, resp.getWriter());
                    break;
                case "phone":
                    context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(2)));
                    engine.process("product/products.html", context, resp.getWriter());
                    break;
                case "all_devices":
                    context.setVariable("products", productDataStore.getAll());
                    engine.process("product/products.html", context, resp.getWriter());
                    break;
            }
        }
    }
}

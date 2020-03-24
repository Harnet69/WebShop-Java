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
        if (req.getParameter("category") == null && req.getParameter("supplier") == null) {
            context.setVariable("products", productDataStore.getAll());
            engine.process("product/index.html", context, resp.getWriter());
        }
        else if(req.getParameter("category").equals("all") && !req.getParameter("supplier").equals("all")){
            context.setVariable("products", productDataStore.getBySupplier(req.getParameter("supplier")));
            engine.process("product/products.html", context, resp.getWriter());
        }
        else if(!req.getParameter("category").equals("all") && req.getParameter("supplier").equals("all")){
            context.setVariable("products", productDataStore.getByCategory(req.getParameter("category")));
            engine.process("product/products.html", context, resp.getWriter());
        }
        else if (req.getParameter("category").equals("all") && req.getParameter("supplier").equals("all")) {
            context.setVariable("products", productDataStore.getAll());
            engine.process("product/products.html", context, resp.getWriter());
        }
        else if(req.getParameter("category") != null && req.getParameter("supplier") != null){
            context.setVariable("products", productDataStore.getByCategorySupplier(req.getParameter("category") ,req.getParameter("supplier")));
            engine.process("product/products.html", context, resp.getWriter());
        }
    }
}

package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if(req.getParameter("category") == null){
            context.setVariable("category", productCategoryDataStore.find(1));
            context.setVariable("products", productDataStore.getAll());
            engine.process("product/index.html", context, resp.getWriter());
        }
        else if(req.getParameter("category").equals("tablet")){
            context.setVariable("category", productCategoryDataStore.find(1));
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
            engine.process("product/products.html", context, resp.getWriter());
        }
        else if(req.getParameter("category").equals("all")){
            context.setVariable("category", productCategoryDataStore.find(1));
            context.setVariable("products", productDataStore.getAll());
            engine.process("product/products.html", context, resp.getWriter());
        }
        else if(req.getParameter("category").equals("phone")){
            context.setVariable("category", productCategoryDataStore.find(2));
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(2)));
            engine.process("product/products.html", context, resp.getWriter());
        }

        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
//        engine.process("product/index.html", context, resp.getWriter());
    }

}

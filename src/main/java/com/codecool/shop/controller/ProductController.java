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

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

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

        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
//        engine.process("product/index.html", context, resp.getWriter());
    }

}

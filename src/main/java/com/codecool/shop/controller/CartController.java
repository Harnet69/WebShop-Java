package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String data = "";
//        System.out.println("The cart was requested");
//        System.out.println(req.getParameter("qttOfProdTypes"));
//        int qttOfProdTypes = Integer.parseInt(req.getParameter("qttOfProdTypes"));
//        for(int i = 1; i<=qttOfProdTypes; i++){
//            System.out.println(req.getParameter(String.valueOf(i)));
//        }
        System.out.println("Data from server"+req.getParameter("data"));
        if(req.getParameter("data") != null){
          data = req.getParameter("data");
            System.out.println(data);
        }

        context.setVariable("name", req.getParameter(data));
        engine.process("product/cart.html", context, resp.getWriter());
    }
}

package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.JDBC.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        resp.setContentType("text/html");

        String email = req.getParameter("email");
        String password = req.getParameter("pwd");
        System.out.println(email + " : " + password);
        try {
            if (password.equals("marek1")) {
                System.out.println("Pass correct");
                Cookie ck = new Cookie("email", email);
                resp.addCookie(ck);
                resp.sendRedirect("order?data=4,5");
            } else {
                System.out.println("Pass incorrect");
                resp.sendRedirect("order.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        context.setVariable("products", productData.getByCategorySupplier(req.getParameter("category"), req.getParameter("supplier")));

//        context.setVariable("products", productData.getByCategorySupplier(req.getParameter("category"), req.getParameter("supplier")));
//        engine.process("product/order.html", context, resp.getWriter());
    }
}

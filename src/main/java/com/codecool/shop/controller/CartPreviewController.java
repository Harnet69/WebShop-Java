package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@WebServlet(urlPatterns = {"/cart-preview"})
public class CartPreviewController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        List<Product> prodInCart = new ArrayList<>();
        List<Product> prodInCartForAmount = new ArrayList<>();

        String[] ary = req.getParameter("data").split(",");
        for(String id : ary) {
            try {
                prodInCartForAmount.add(productDataStore.find(Integer.parseInt(id)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // add products and it quantities to array
        for(String id : ary){
            long prodQtt = Arrays.stream(ary)
                    .filter(x -> x.equals(id))
                    .count();
            try {
                productDataStore.find(Integer.parseInt(id)).setQuantity((int) prodQtt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(!prodInCart.contains(productDataStore.find(Integer.parseInt(id)))){
                    prodInCart.add(productDataStore.find(Integer.parseInt(id)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // calculate amount of the cart
        List<String> amount = prodInCartForAmount.stream()
                .map(Product::getPrice)
                .collect(Collectors.toList());
        List<Double> sliced = amount.stream().map(x -> x.substring(0, x.length()- 4)).map(Double::valueOf).collect(Collectors.toList());
        double amoutOfCart = sliced.stream().mapToDouble(x -> x).sum();
        context.setVariable("data", prodInCart);
        context.setVariable("amount", amoutOfCart);
        engine.process("product/cart-preview.html", context, resp.getWriter());
    }
}

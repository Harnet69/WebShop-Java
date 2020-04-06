package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.JDBC.ProductDaoJdbc;
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
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<Product> prodInCart = new ArrayList<>();
        List<Product> prodInCartForAmount = new ArrayList<>();
        int maxProdQttForSale = 10;

        if(req.getParameter("data") != null) {
            String[] ary = req.getParameter("data").split(",");
            for (String id : ary) {
                try {
                    Integer.parseInt(id);
                } catch (NumberFormatException e) {
//                e.printStackTrace();
                    continue;
                }
                try {
                    if (productDataStore.find(Integer.parseInt(id)) != null) {
                        prodInCartForAmount.add(productDataStore.find(Integer.parseInt(id)));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // add products and it quantities to array
            for (String id : ary) {
                long prodQtt = Arrays.stream(ary)
                        .filter(x -> x.equals(id))
                        .count();
                try {
                    Integer.parseInt(id);
                } catch (Exception e) {
//                    e.printStackTrace();
                    continue;
                }
                try {
                    if (productDataStore.find(Integer.parseInt(id)) != null) {
                        if (prodQtt <= 10) {
                            productDataStore.find(Integer.parseInt(id)).setQuantity((int) prodQtt);
                        } else {
                            productDataStore.find(Integer.parseInt(id)).setQuantity(maxProdQttForSale);
                        }
                        if (!prodInCart.contains(productDataStore.find(Integer.parseInt(id)))) {
                            prodInCart.add(productDataStore.find(Integer.parseInt(id)));
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            context.setVariable("data", sortProdInCart(prodInCart));
            context.setVariable("amount", calcCartAmount(prodInCartForAmount));
            engine.process("product/cart.html", context, resp.getWriter());
        }else{
            try {
                context.setVariable("products", productDataStore.getAll());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            engine.process("product/index.html", context, resp.getWriter());
        }
    }

    // sort products in Cart by id
    public List<Product> sortProdInCart(List<Product> prodInCart) {
        Collections.sort(prodInCart, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });
        return prodInCart;
    }

    // sort winners
//    private List<Product> sortWinners(Object prodInCart){
//        Collections.sort(prodInCart, new Comparator<Product>() {
//            @Override
//            public int compare(Product o1, Product o2) {
//                return Integer.compare(o2.getId(), o1.getId());
//            }
//        });
//
//        return product;
//    }
    // calculate amount of the cart
    public double calcCartAmount(List<Product> prodInCartForAmount) {
        List<String> amount = prodInCartForAmount.stream()
                .map(Product::getPrice)
                .collect(Collectors.toList());
        List<Double> sliced = amount.stream().map(x -> x.substring(0, x.length() - 4)).map(Double::valueOf).collect(Collectors.toList());
        return sliced.stream().mapToDouble(x -> x).sum();
    }
}
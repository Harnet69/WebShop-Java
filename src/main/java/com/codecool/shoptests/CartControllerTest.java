package com.codecool.shoptests;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.controller.CartController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.thymeleaf.TemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;

class CartControllerTest extends Mockito {


    HttpServletRequest testRequest = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse testResponse = Mockito.mock(HttpServletResponse.class);


    @Test
    void TestDoGet() throws IOException {
        when(testRequest.getParameter("data")).thenReturn("Test data requested");

        ServletContext servletContext = Mockito.mock(ServletContext.class);
        when(testRequest.getServletContext()).thenReturn(servletContext);

        TemplateEngine engine = new TemplateEngine();
        when(TemplateEngineUtil.getTemplateEngine(testRequest.getServletContext())).thenReturn((engine));

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(testResponse.getWriter()).thenReturn(writer);

        CartController cartController = new CartController() {
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        try {
            cartController.doGet(testRequest, testResponse);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

        verify(testRequest, atLeast(1)).getParameter("data");
        assertTrue(servletContext.toString().contains("Test data requested"));
    }
}
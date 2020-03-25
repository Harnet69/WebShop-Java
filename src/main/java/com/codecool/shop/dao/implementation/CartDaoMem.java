package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDaoMem {
    private List<Cart> data = new ArrayList<>();

    public void addCartToData(Cart cart){
        data.add(cart);
    }
}

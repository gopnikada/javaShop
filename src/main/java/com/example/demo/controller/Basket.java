package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Order> orders = new ArrayList<Order>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(Order order) {
        this.orders.add(order);
    }

    public Basket(List<Order> basket) {
        this.orders = basket;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "basket=" + orders +
                '}';
    }
}

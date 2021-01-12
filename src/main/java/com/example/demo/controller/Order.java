package com.example.demo.controller;

import ch.qos.logback.core.util.InvocationGate;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private int id;
    private int count;

    public Order(int id, int count, List<Integer> list) {
        this.id = id;
        this.count = count;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Order(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        this.count = obj.getInt("count");
        this.id = obj.getInt("id");


    }

    @Override
    public String toString() {
        return "Order with id " + id +
                " in count " + count +
                " has been purchased";
    }
}

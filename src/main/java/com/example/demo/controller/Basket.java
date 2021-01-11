package com.example.demo.controller;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
    private int id;
    private int count;

    public Basket(int id, int count) {
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

    public Basket(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        this.count = obj.getInt("count");
        this.id = obj.getInt("id");
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", count=" + count +
                '}';
    }
}

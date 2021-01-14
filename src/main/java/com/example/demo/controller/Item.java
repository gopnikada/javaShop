package com.example.demo.controller;

import org.json.JSONException;
import org.json.JSONObject;

import java.awt.event.ItemEvent;

public class Item {
    private int id;
    private String name;
    private int countAvailable;
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Item(int id, String name, int countAvailable) {
        this.id = id;
        this.name = name;
        this.countAvailable = countAvailable;
        this.link = "http://127.0.0.1:8080/items/" + this.id;
    }

    public Item(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        this.name = obj.getString("name");
        this.countAvailable = obj.getInt("countAvailable");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountAvailable() {
        return countAvailable;
    }

    public void setCountAvailable(int countAvailable) {
        this.countAvailable = countAvailable;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countAvailable=" + countAvailable +
                ", link='" + link + '\'' +
                '}';
    }
}

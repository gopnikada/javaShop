package com.example.demo.controller;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class BusketMul {
    private List<Map<String, Integer>> itemsIdCountList = new AbstractList<Map<String, Integer>>() {
        @Override
        public Map<String, Integer> get(int index) {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }
    };

    public BusketMul(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        int id = obj.getInt("id");
        int count = obj.getInt("count");

        HashMap<String, Integer> data = new HashMap<String, Integer>();
        data.put("id", id);
        data.put("count", count);
        List<Map<String, Integer>> mockList = new ArrayList<Map<String, Integer>>(){};
        mockList.add(data);
        this.itemsIdCountList = mockList;
    }

    public BusketMul(List<Map<String, Integer>> itemsIdCountList) {
        this.itemsIdCountList = itemsIdCountList;
    }

    public List<Map<String, Integer>> getItemsIdCountList() {
        return itemsIdCountList;
    }

    public void setItemsIdCountList(List<Map<String, Integer>> itemsIdCountList) {
        this.itemsIdCountList = itemsIdCountList;
    }

    @Override
    public String toString() {
        return "BusketMul{" +
                "itemsIdCountList=" + itemsIdCountList +
                '}';
    }
}

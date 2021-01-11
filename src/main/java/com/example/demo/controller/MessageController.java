package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("items")
public class MessageController {


    private List<Item> items = new ArrayList<Item>(){{
        add(new Item(1, "Samsung A5", 3));
        add(new Item(2, "HTC XC", 2));
        add(new Item(3, "BlackBerry", 0));
    }};

    private int idCounter = items.size();


    @GetMapping
    public List<Item> list(){
        return items;
    }

    @GetMapping("{id}")
    public Item getOne(@PathVariable String id) {
        return getItem(id);
    }



    @PostMapping
    public List<Item> create(@RequestBody Item item) {
        item.setId(++idCounter);
        items.add(item);
        return items;
    }
//    @PutMapping
//    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
//        Map<String, String> messageFromDb = getMessage(id);
//        messageFromDb.putAll(message);
//        messageFromDb.put("id", id);
//        return messageFromDb;
//    }
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable String id){
//        Map<String, String> message = getMessage(id);
//        messages.remove(message);
//    }
//
//
    private Item getItem(String id) {
        return items.stream()
                .filter(t -> Integer.parseInt(id)==t.getId())
                .findFirst()
                .orElse(null);
    }
}

package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("items")
public class ItemController {
    public static List<Item> items = new ArrayList<Item>(){{
        add(new Item(1, "Samsung A5", 7));
        add(new Item(2, "HTC XC", 8));
        add(new Item(3, "BlackBerry", 5));
    }};
    public static int getIdCounter=items.size();
    Basket basket = new Basket(new ArrayList<Order>());


    @GetMapping
    public List<Item> list(){
        return items;
    }

    @GetMapping("{id}")
    public Item getOne(@PathVariable String id) {
        return getItem(id);
    }

    @PostMapping
    public List<Item> addOne(@RequestBody Item item) {
        item.setId(++getIdCounter);
        System.out.println(item.toString());
        items.add(item);
        return items;
    }
    @PostMapping("buy")
    public String addToBasket(@RequestBody Order order) {
        System.out.println(order.toString());
        String output = "";

        for(int i = 0; i < items.size(); i++){
            if(order.getId() == items.get(i).getId() && order.getCount()<=items.get(i).getCountAvailable()){
                output = order.toString() +" =>> has id and count";
                basket.setOrders(order);
                System.out.println(basket.getOrders());
                break;
            }else{
                output = "not found or no such count";
            }
        }

        return output;
    }
//    @PostMapping("buyMul")
//    public BusketMul addMul(@RequestBody BusketMul basketMul){
//        System.out.println(basketMul.toString());
//        return basketMul;
//    }


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

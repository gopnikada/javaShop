package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("items")
public class ItemController {
    public static List<Item> items = new ArrayList<Item>(){{
        add(new Item(1, "Samsung A5", 7));
        add(new Item(2, "HTC XC", 8));
        add(new Item(3, "BlackBerry", 5));
    }};
    public static int getIdCounter = items.size();
    Basket basket = new Basket(new ArrayList<Order>());


    @GetMapping
    public List<Item> list(){
        return items;
    }
    @GetMapping("basket")
    public List<Order> clientBasket(){
        return basket.getOrders();
    }

    @GetMapping("{id}")
    public Item getOne(@PathVariable String id) {

        return getItem(id);
    }

    @PostMapping
    public List<Item> addOne(@RequestBody Item item) {
        item.setId(++getIdCounter);
        item.setLink("http://127.0.0.1:8080/items/" + item.getId());
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
                Item item = getItem(String.valueOf(order.getId()));
                item.setCountAvailable(item.getCountAvailable() - order.getCount());
                output = order.toString() +" =>> has id and count";
                if(basket.getOrders().size()==0 ){
                    basket.setOrders(order);
                }else {
                    if((matchedOrder(order.getId()).isEmpty())){
                        basket.setOrders(order);
                    }else{
                        Item editedItem = getItem(String.valueOf(order.getId()));
                        editedItem.setCountAvailable(editedItem.getCountAvailable()+1);
                        editedItem.setCountAvailable(editedItem.getCountAvailable() - order.getCount());
                        foundOrder(order.getId()).setCount(foundOrder(order.getId()).getCount()+order.getCount());
                    }
                }
                break;
            }else{
                output = "not found or no such count";
            }
        }

        return output;
    }

    private Item getItem(String id) {
        return items.stream()
                .filter(t -> Integer.parseInt(id)==t.getId())
                .findFirst()
                .orElse(null);
    }
    private Optional<Order> matchedOrder(int id) {
        return basket.getOrders().stream()
                .filter(o -> id == o.getId())
                .findFirst();
    }
    private Order foundOrder(int id) {
        return basket.getOrders().stream()
                .filter(o -> id == o.getId())
                .findFirst()
                .orElse(null);
    }
}

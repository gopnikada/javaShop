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
    public static int getIdCounter = items.size();
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
                Item item = getItem(String.valueOf(order.getId()));
                //System.out.println("Item to add id: " + item.getId());
                item.setCountAvailable(item.getCountAvailable() - order.getCount());
                //System.out.println("Rest item count: " + item.getCountAvailable());
                output = order.toString() +" =>> has id and count";
                if(basket.getOrders().size()==0){
                    basket.setOrders(order);
                }

                addOrder(order);


//                if(order.getId() == getOrder(String.valueOf(order.getId())).getId()) {
//                    basket.setOrders(order);
//                }else{
//                    System.out.println("this order already in basket");
//                }

                System.out.println("---------------------------------------------------");

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

    private Order getOrder(String id) {
        return basket.getOrders().stream()
                .filter(t -> id==String.valueOf(t.getId()))
                .findFirst()
                .orElse(null);
    }

    private void addOrder(Order order) {
        for (Order o : basket.getOrders()) {
            if(order.getId() != o.getId()){
                System.out.println("added");
                basket.setOrders(o);
            }else{
                System.out.println("not unique");
            }
        }

    }
}

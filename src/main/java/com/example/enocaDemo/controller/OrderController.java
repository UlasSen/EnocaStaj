package com.example.enocaDemo.controller;

import com.example.enocaDemo.model.Order;
import com.example.enocaDemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/place/{cartId}")
   public Order placeOrder(@PathVariable Long cartId){
        return orderService.placeOrder(cartId);
    }
    @GetMapping("/{orderId}")
    public Order getOrderForCode(@PathVariable Long orderId){
        return orderService.getOrderForCode(orderId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getAllOrdersForCustomer(@PathVariable Long customerId){
        List<Order> orders=orderService.getAllOrdersForCustomer(customerId);
        return orders;
    }
}

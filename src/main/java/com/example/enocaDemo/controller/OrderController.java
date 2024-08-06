package com.example.enocaDemo.controller;

import com.example.enocaDemo.dto.OrderDto;
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
   public OrderDto placeOrder(@PathVariable Long cartId){
        return orderService.placeOrder(cartId);
    }
    @GetMapping("/{orderId}")
    public OrderDto getOrderForCode(@PathVariable Long orderId){
        return orderService.getOrderForCode(orderId);
    }

    @GetMapping("/customer/{customerId}")
    public List<OrderDto> getAllOrdersForCustomer(@PathVariable Long customerId){
        List<OrderDto> orders=orderService.getAllOrdersForCustomer(customerId);
        return orders;
    }
}

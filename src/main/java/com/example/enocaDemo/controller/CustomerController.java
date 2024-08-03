package com.example.enocaDemo.controller;

import com.example.enocaDemo.model.Customer;
import com.example.enocaDemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public void addCustomer(@RequestBody Customer customer){
         customerService.addCustomer(customer);
    }

   @GetMapping
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

}

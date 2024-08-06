package com.example.enocaDemo.controller;

import com.example.enocaDemo.dto.CustomerDto;
import com.example.enocaDemo.model.Customer;
import com.example.enocaDemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addCustomer(@RequestBody CustomerDto customer){
         customerService.addCustomer(customer);
    }

   @GetMapping
    public List<CustomerDto> getAllCustomer(){
        return customerService.getAllCustomer();
    }

}

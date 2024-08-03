package com.example.enocaDemo.service;

import com.example.enocaDemo.model.Cart;
import com.example.enocaDemo.model.Customer;
import com.example.enocaDemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartService cartService;

    public void addCustomer(Customer customer){
         Customer savedCustomer = customerRepository.save(customer);
         Cart cart= new Cart();
         cart.setCustomer(savedCustomer);
         cart.setTotalPrice(0.0);
         cartService.createCart(cart);
         savedCustomer.setCart(cart);

         customerRepository.save(savedCustomer);
    }
    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }


}

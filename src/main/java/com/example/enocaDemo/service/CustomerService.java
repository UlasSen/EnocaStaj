package com.example.enocaDemo.service;

import com.example.enocaDemo.dto.CustomerDto;
import com.example.enocaDemo.model.Cart;
import com.example.enocaDemo.model.Customer;
import com.example.enocaDemo.repository.CartRepository;
import com.example.enocaDemo.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    public void addCustomer(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        Cart cart = new Cart();
        cart.setTotalPrice(0.0);
        cart.setCustomer(customer);
        customer.setCart(cart);
        customerRepository.save(customer);
        cartRepository.save(cart);
    }

    public List<CustomerDto> getAllCustomer(){
        List<Customer> customers =customerRepository.findAll();
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }


}

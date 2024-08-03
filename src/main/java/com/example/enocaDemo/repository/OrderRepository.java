package com.example.enocaDemo.repository;

import com.example.enocaDemo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Order findById(long id);
    List<Order> findByCustomerId(long customerId);
}

package com.example.enocaDemo.repository;

import com.example.enocaDemo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}

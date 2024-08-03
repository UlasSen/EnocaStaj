package com.example.enocaDemo.repository;

import com.example.enocaDemo.model.Cart;
import com.example.enocaDemo.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findByCart(Cart cart);
}

package com.example.enocaDemo.service;

import com.example.enocaDemo.model.*;
import com.example.enocaDemo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order placeOrder(Long cartId) {
        // Sepeti al ve geçerli olup olmadığını kontrol et
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));


        // Yeni bir sipariş oluştur
        Order order = new Order();
        order.setCustomer(cart.getCustomer());
        order.setTotalPrice(cart.getTotalPrice());
        orderRepository.save(order);

        // Sepetteki ürünleri siparişe taşı
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtPurchase(cartItem.getProduct().getPrice());
            orderItemRepository.save(orderItem);

            // Ürün stoklarını güncelle
            Product product = cartItem.getProduct();
            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);
        }

        // Sepeti boşalt
        cartItemRepository.deleteAll(cartItems);
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);

        return order;
    }

    public Order getOrderForCode(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public List<Order> getAllOrdersForCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}


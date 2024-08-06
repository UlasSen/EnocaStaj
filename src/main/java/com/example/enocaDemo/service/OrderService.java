package com.example.enocaDemo.service;

import com.example.enocaDemo.dto.CustomerDto;
import com.example.enocaDemo.dto.OrderDto;
import com.example.enocaDemo.dto.OrderItemDto;
import com.example.enocaDemo.model.*;
import com.example.enocaDemo.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ModelMapper modelMapper;

    public OrderDto placeOrder(Long cartId) {
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

        return modelMapper.map(order,OrderDto.class);
    }

    public OrderDto getOrderForCode(Long orderId) {
        Order order= orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Not found"));
        return modelMapper.map(order,OrderDto.class);
    }

    public List<OrderDto> getAllOrdersForCustomer(Long customerId) {
        List<Order> orders= orderRepository.findByCustomerId(customerId);
        return orders.stream()
                .map(customer -> modelMapper.map(orders, OrderDto.class))
                .collect(Collectors.toList());
    }
}


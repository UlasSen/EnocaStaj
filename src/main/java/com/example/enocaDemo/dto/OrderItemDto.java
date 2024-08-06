package com.example.enocaDemo.dto;

import com.example.enocaDemo.model.Order;
import com.example.enocaDemo.model.Product;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private OrderDto order;
    private ProductDto product;
    private Integer quantity;
    private Double priceAtPurchase;
}

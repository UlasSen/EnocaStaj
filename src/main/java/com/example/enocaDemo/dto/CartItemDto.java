package com.example.enocaDemo.dto;

import com.example.enocaDemo.model.Cart;
import com.example.enocaDemo.model.Product;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private ProductDto product;
    private Integer quantity=0;
    private Double totalPrice;
}

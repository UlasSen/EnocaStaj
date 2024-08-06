package com.example.enocaDemo.dto;

import com.example.enocaDemo.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private List<OrderItemDto> items = new ArrayList<>();
    private Double totalPrice;
}

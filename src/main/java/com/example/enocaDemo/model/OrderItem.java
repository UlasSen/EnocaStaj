package com.example.enocaDemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem extends BaseEntity{
    @ManyToOne
    @JsonBackReference
    private Order order;

    @ManyToOne
    private Product product;

    private Integer quantity;
    private Double priceAtPurchase;
}

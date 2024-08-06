package com.example.enocaDemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "customer_order")
public class Order extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference("customer-orders")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<OrderItem> items = new ArrayList<>();

    private Double totalPrice;
}

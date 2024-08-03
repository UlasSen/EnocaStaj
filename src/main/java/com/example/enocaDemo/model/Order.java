package com.example.enocaDemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "customer_order")
public class Order extends BaseEntity{
    @ManyToOne
    @JsonBackReference
    private Customer customer;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<OrderItem> items = new ArrayList<>();

    private Double totalPrice;
}

package com.example.enocaDemo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {

    @ManyToOne
    @JsonBackReference
    private Customer customer;

    @OneToMany(mappedBy = "cart",orphanRemoval = true)
    @JsonManagedReference
    private List<CartItem> items;

    @Column(nullable = false)
    private Double totalPrice;
}
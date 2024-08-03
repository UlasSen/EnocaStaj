package com.example.enocaDemo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cart_item")
public class CartItem extends BaseEntity {

    @ManyToOne
    @JsonBackReference
    private Cart cart;

    @ManyToOne
    private Product product;

    private Integer quantity=0;
    private Double totalPrice;

}

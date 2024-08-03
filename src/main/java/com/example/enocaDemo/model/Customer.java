package com.example.enocaDemo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "customer")
public class Customer extends BaseEntity{
    private String name;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Order> orders;

    @OneToOne(mappedBy = "customer")
    @JsonManagedReference
    private Cart cart;
}

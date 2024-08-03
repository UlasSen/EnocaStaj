package com.example.enocaDemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product extends BaseEntity{
    private String name;
    private Double price;
    private Integer stock;

}

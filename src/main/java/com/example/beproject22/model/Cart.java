package com.example.beproject22.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private Double price;
}

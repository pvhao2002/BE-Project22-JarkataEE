package com.example.beproject22.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Integer id;
    private Integer cartId;
    private Integer productId;
    private Integer quantity;
    private Double price;
}

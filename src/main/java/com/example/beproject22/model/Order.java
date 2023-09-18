package com.example.beproject22.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;
    private Integer userId;
    private String address;
    private String phone;
    private String status;
    private Double total;
}
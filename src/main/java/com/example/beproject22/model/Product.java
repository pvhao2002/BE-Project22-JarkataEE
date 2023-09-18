package com.example.beproject22.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String productName;
    private String description;
    private String category;
    private String author;
    private Double price;
    private String image;
    private Boolean isDeleted;
}

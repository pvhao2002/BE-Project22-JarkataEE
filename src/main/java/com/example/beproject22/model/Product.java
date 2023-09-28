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

/*
{
    "id": 1,
    "productName": "Sách Toán 1",
    "description": "Sách Toán 1",
    "category": "Sách Toán",
    "author": "Nguyễn Văn A",
    "price": 100000,
    "image": "https://salt.tikicdn.com/cache/550x550/ts/product/4f/2e/4a/8f3f3b2b0b3b2b2b2b2b2b2b2b2b2b2.jpg",
    "isDeleted": false
}
*/
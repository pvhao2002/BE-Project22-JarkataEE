package com.example.beproject22.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer id;
    private Integer userId;
    private String address;
    private String phone;
    private String status;
    private String note;
    private Double total;
    private Date createdAt;

    private User user;

    private Set<OrderItem> orderItems = new HashSet<>();
}

package com.example.beproject22.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Dashboard {
    private Integer totalActiveUser;
    private Integer totalInactiveUser;
    private Integer totalProduct;
    private Integer totalOrders;
    private Integer totalProductSales;
    private Double totalRevenue;
}

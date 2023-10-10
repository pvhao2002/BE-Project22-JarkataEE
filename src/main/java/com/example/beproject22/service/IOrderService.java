package com.example.beproject22.service;

import com.example.beproject22.model.Order;

import java.util.List;

public interface IOrderService {
    Object add(Order order) throws Exception;
    List<Order> getAllOrderOfUser(int userId) throws Exception;

    List<Order> getAll() throws Exception;
}

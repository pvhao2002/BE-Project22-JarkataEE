package com.example.beproject22.service;

import com.example.beproject22.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll() throws Exception;
    Object add(Product product) throws Exception;
    Object update(Product product) throws Exception;
    Object delete(int id) throws Exception;
    Product getById(int id) throws Exception;
    List<Product> getNewProduct() throws Exception;
}

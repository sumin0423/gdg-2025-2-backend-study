package com.example.shop.product;

import java.util.List;

public interface ProductRepository {

    Product findById(Long id);

    List<Product> findAll();

    void save(Product product);

    void deleteById(Long id);
}
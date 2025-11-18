package com.example.shop.product.dto;

import com.example.shop.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Integer price;
    private Integer stock;
    private String description;
    private String status;

    public static ProductResponse from(Product p) {
        return new ProductResponse(
                p.getId(), p.getName(), p.getPrice(),
                p.getStock(), p.getDescription(), p.getStatus()
        );
    }
}
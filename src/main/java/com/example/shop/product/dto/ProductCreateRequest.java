package com.example.shop.product.dto;

import lombok.Getter;

@Getter
public class ProductCreateRequest {
    private String name;
    private Integer price;
    private Integer stock;
    private String description;
    private String status; // "ON_SALE", "SOLD_OUT" 등
}

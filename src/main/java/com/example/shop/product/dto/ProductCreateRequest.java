package com.example.shop.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductCreateRequest {
    private String name;
    private Integer price;
    private Integer stock;
    private String description;
    private String status;
}
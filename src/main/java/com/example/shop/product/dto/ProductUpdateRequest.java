package com.example.shop.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductUpdateRequest {
    private String name;        // 선택 수정 가능
    private Integer price;
    private Integer stock;
    private String description;
    private String status;
}
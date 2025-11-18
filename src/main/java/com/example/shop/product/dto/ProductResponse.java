package com.example.shop.product.dto;

import com.example.shop.product.Product; // ← 엔티티 경로가 이게 맞으면 그대로 두고,
// 만약 엔티티를 entity 패키지로 빼놨다면:
// import com.example.shop.product.entity.Product;

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

    // ★ 반드시 엔티티 Product를 받도록!
    public static ProductResponse from(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getStock(),
                p.getDescription(),
                p.getStatus()
        );
    }
}
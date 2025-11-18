package com.example.shop.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;        // 상품명
    private Integer price;      // 가격
    private Integer stock;      // 재고 수량
    private String description; // 설명
    private String status;      // 상태 (e.g. "ON_SALE", "SOLD_OUT")
}
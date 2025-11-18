package com.example.shop.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {
    private Long productId;
    private int price;     // 단가
    private int quantity;  // 수량
}
package com.example.shop.order;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private Long memberId;            // ❗ int → Long
    private Integer totalPrice;       // 계산값(nullable 허용하면 Integer)
    private String status;            // "CREATED", "CANCELED" 등
    private LocalDateTime createdAt;
    private List<OrderItem> items;

    public Order(Long id, Long memberId, List<OrderItem> items) { // ❗ 시그니처 Long 사용
        this.id = id;
        this.memberId = memberId;
        this.items = items;
        this.totalPrice = items.stream()
                .map(i -> i.getPrice() * i.getQuantity())
                .reduce(0, Integer::sum);
        this.status = "CREATED";
        this.createdAt = LocalDateTime.now();
    }

    // === getters ===
    public Long getId() { return id; }
    public Long getMemberId() { return memberId; }
    public Integer getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<OrderItem> getItems() { return items; }

    // ❗ 없어서 에러난 메서드
    public void cancel() {
        this.status = "CANCELED";
    }
}
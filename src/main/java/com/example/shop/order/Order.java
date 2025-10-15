package com.example.shop.order;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Order {
    private final Long id;
    private final Long memberId;
    private final List<OrderItem> items;
    private int totalPrice;
    private OrderStatus status;
    private final LocalDateTime createdAt;

    public Order(Long id, Long memberId, List<OrderItem> items) {
        this.id = id;
        this.memberId = memberId;
        this.items = items;
        this.totalPrice = calcTotal(items);
        this.status = OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
    }

    private int calcTotal(List<OrderItem> items) {
        return items.stream()
                .mapToInt(i -> i.getPrice() * i.getQuantity())
                .sum();
    }

    public void cancel() {
        if (this.status == OrderStatus.CANCELED) return;
        this.status = OrderStatus.CANCELED;
    }
}
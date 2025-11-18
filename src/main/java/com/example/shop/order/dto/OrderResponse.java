package com.example.shop.order.dto;

import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponse {
    private Long id;
    private Long memberId;           // nullable
    private Integer totalPrice;      // nullable
    private String status;           // nullable
    private LocalDateTime createdAt; // nullable
    private List<Item> items;        // nullable/empty 가능

    @Getter
    public static class Item {
        private Long productId;
        private Integer price;
        private Integer quantity;

        public Item(Long productId, Integer price, Integer quantity) {
            this.productId = productId;
            this.price = price;
            this.quantity = quantity;
        }

        // OrderItem 아직 없으면 이 정적 메서드는 만들지 말거나 주석 처리하세요.
        // public static Item from(OrderItem i) { ... }
    }

    public OrderResponse(Long id,
                         Long memberId,
                         Integer totalPrice,
                         String status,
                         LocalDateTime createdAt,
                         List<Item> items) {
        this.id = id;
        this.memberId = memberId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.items = items;
    }

    // Order 엔티티에 필드가 아직 부족해도 돌아가도록 임시 매핑
    public static OrderResponse from(com.example.shop.order.Order o) {
        return new OrderResponse(
                o.getId(),
                null,        // memberId: Order에 생기면 o.getMemberId()로 교체
                null,        // totalPrice: o.getTotalPrice()
                null,        // status: o.getStatus()
                null,        // createdAt: o.getCreatedAt()
                List.of()    // items: 나중에 OrderItem 생기면 매핑
        );
    }
}
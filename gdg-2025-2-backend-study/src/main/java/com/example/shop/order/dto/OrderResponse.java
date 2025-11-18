package com.example.shop.order.dto;

import com.example.shop.order.Order;
import com.example.shop.order.OrderItem;
import com.example.shop.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private Long memberId;
    private int totalPrice;
    private OrderStatus status;
    private LocalDateTime createdAt;
    private List<Item> items;

    @Getter
    @AllArgsConstructor
    public static class Item {
        private Long productId;
        private int price;
        private int quantity;

        public static Item from(OrderItem i) {
            return new Item(i.getProductId(), i.getPrice(), i.getQuantity());
        }
    }

    public static OrderResponse from(Order o) {
        return new OrderResponse(
                o.getId(),
                o.getMemberId(),
                o.getTotalPrice(),
                o.getStatus(),
                o.getCreatedAt(),
                o.getItems().stream().map(Item::from).toList()
        );
    }
}
package com.example.shop.order;

public class OrderItem {
    private Long productId;
    private Integer price;
    private Integer quantity;

    public OrderItem(Long productId, Integer price, Integer quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public Long getProductId() { return productId; }
    public Integer getPrice() { return price; }
    public Integer getQuantity() { return quantity; }
}
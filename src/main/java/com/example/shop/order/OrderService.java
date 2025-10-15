package com.example.shop.order;

import com.example.shop.order.dto.OrderCreateRequest;
import com.example.shop.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class OrderService {

    // 메모리 저장소(이번 과제용)
    private final Map<Long, Order> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    /** 주문 생성 */
    public Long create(OrderCreateRequest req) {
        validateCreate(req);
        Long id = sequence.incrementAndGet();

        List<OrderItem> items = req.getItems().stream()
                .map(i -> new OrderItem(i.getProductId(), i.getPrice(), i.getQuantity()))
                .toList();

        Order order = new Order(id, req.getMemberId(), items);
        store.put(id, order);
        return id;
    }

    /** 주문 목록 */
    public List<OrderResponse> findAll() {
        return store.values().stream()
                .sorted(Comparator.comparing(Order::getId))
                .map(OrderResponse::from)
                .toList();
    }

    /** 주문 상세 */
    public OrderResponse findOne(Long id) {
        Order o = store.get(id);
        if (o == null) throw new NoSuchElementException("주문이 존재하지 않습니다. id=" + id);
        return OrderResponse.from(o);
    }

    /** 주문 취소 */
    public void cancel(Long id) {
        Order o = store.get(id);
        if (o == null) throw new NoSuchElementException("주문이 존재하지 않습니다. id=" + id);
        o.cancel();
    }

    private void validateCreate(OrderCreateRequest req) {
        if (req.getMemberId() == null)
            throw new IllegalArgumentException("memberId는 필수입니다.");
        if (req.getItems() == null || req.getItems().isEmpty())
            throw new IllegalArgumentException("items는 1개 이상이어야 합니다.");
        boolean invalid = req.getItems().stream()
                .anyMatch(i -> i.getQuantity() <= 0 || i.getPrice() < 0 || i.getProductId() == null);
        if (invalid) throw new IllegalArgumentException("item의 productId/price/quantity를 확인하세요.");
    }
}
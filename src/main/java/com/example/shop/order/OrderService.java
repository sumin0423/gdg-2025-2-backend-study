package com.example.shop.order;

import com.example.shop.order.dto.OrderCreateRequest;
import com.example.shop.order.dto.OrderResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final Map<Long, Order> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Transactional
    public Long create(OrderCreateRequest req) {
        validateCreate(req);
        Long id = sequence.incrementAndGet(); // long → Long 오토박싱

        List<OrderItem> items = req.getItems().stream()
                .map(i -> new OrderItem(i.getProductId(), i.getPrice(), i.getQuantity()))
                .toList();

        // ❗ 두 번째 파라미터도 Long
        Order order = new Order(id, req.getMemberId(), items);
        store.put(id, order);
        return id;
    }

    public List<OrderResponse> findAll() {
        return store.values().stream()
                .sorted(Comparator.comparing(Order::getId))
                .map(OrderResponse::from)
                .toList();
    }

    public OrderResponse findOne(Long id) {
        Order o = store.get(id);
        if (o == null) throw new NoSuchElementException("order not found: " + id);
        return OrderResponse.from(o);
    }

    @Transactional
    public void cancel(Long id) {
        Order o = store.get(id);
        if (o == null) throw new NoSuchElementException("order not found: " + id);
        o.cancel(); // ❗ Order.cancel() 존재해야 함
    }

    private void validateCreate(OrderCreateRequest req) {
        // 필요 시 검증 추가
    }
}
package com.example.shop.order;

import com.example.shop.order.dto.OrderCreateRequest;
import com.example.shop.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    /** 주문 생성 */
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody OrderCreateRequest req) {
        Long id = orderService.create(req);
        return ResponseEntity.created(URI.create("/orders/" + id)).body(id);
    }

    /** 주문 목록 */
    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    /** 주문 상세 */
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.findOne(id));
    }

    /** 주문 취소 */
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable("id") Long id) {
        orderService.cancel(id);
        return ResponseEntity.noContent().build();
    }
}
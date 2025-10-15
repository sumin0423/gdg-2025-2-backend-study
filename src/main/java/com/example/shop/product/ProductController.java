package com.example.shop.product;

import com.example.shop.product.dto.ProductCreateRequest;
import com.example.shop.product.dto.ProductResponse;
import com.example.shop.product.dto.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // 생성
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody ProductCreateRequest req) {
        Long id = productService.create(req);
        return ResponseEntity.created(URI.create("/products/" + id)).body(id);
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    // 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findOne(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.findOne(id));
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id,
                                       @RequestBody ProductUpdateRequest req) {
        productService.update(id, req);
        return ResponseEntity.noContent().build();
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
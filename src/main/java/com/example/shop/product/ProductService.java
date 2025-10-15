package com.example.shop.product;

import com.example.shop.product.dto.ProductCreateRequest;
import com.example.shop.product.dto.ProductResponse;
import com.example.shop.product.dto.ProductUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ProductService {

    // 임시 메모리 저장소 (레포지토리 없이)
    private final Map<Long, Product> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    /** 상품 생성: ID 발급 후 저장, ID 반환 */
    public Long create(ProductCreateRequest req) {
        if (req.getName() == null || req.getName().isBlank()) {
            throw new IllegalArgumentException("상품명(name)은 필수입니다.");
        }
        Long id = seq.incrementAndGet();
        Product p = new Product(
                id,
                req.getName(),
                defaultInt(req.getPrice(), 0),
                defaultInt(req.getStock(), 0),
                req.getDescription(),
                req.getStatus() == null ? "ON_SALE" : req.getStatus()
        );
        store.put(id, p);
        return id;
    }

    /** 전체 조회 */
    public List<ProductResponse> findAll() {
        return store.values().stream()
                .sorted(Comparator.comparing(Product::getId))
                .map(ProductResponse::from)
                .collect(Collectors.toList());
    }

    /** 단건 조회 */
    public ProductResponse findOne(Long id) {
        Product p = store.get(id);
        if (p == null) throw new NoSuchElementException("상품이 존재하지 않습니다. id=" + id);
        return ProductResponse.from(p);
    }

    /** 수정 */
    public void update(Long id, ProductUpdateRequest req) {
        Product p = store.get(id);
        if (p == null) throw new NoSuchElementException("상품이 존재하지 않습니다. id=" + id);

        if (req.getName() != null)        p.setName(req.getName());
        if (req.getPrice() != null)       p.setPrice(req.getPrice());
        if (req.getStock() != null)       p.setStock(req.getStock());
        if (req.getDescription() != null) p.setDescription(req.getDescription());
        if (req.getStatus() != null)      p.setStatus(req.getStatus());

        store.put(id, p); // 덮어쓰기
    }

    /** 삭제 */
    public void delete(Long id) {
        if (store.remove(id) == null) {
            throw new NoSuchElementException("상품이 존재하지 않습니다. id=" + id);
        }
    }

    private int defaultInt(Integer v, int def) {
        return v == null ? def : v;
    }
}
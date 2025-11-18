package com.example.shop.product;

import com.example.shop.product.dto.ProductCreateRequest;
import com.example.shop.product.dto.ProductResponse;
import com.example.shop.product.dto.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse create(ProductCreateRequest req) {
        Product p = Product.builder()
                .name(req.getName())
                .price(req.getPrice())
                .stock(req.getStock())
                .description(req.getDescription())
                .status(req.getStatus())
                .build();

        productRepository.save(p);
        return ProductResponse.from(p);
    }

    private Product findEntity(Long id) {
        Product p = productRepository.findById(id);
        if (p == null) {
            throw new IllegalArgumentException("Product not found: " + id);
        }
        return p;
    }

    public ProductResponse get(Long id) {
        return ProductResponse.from(findEntity(id));
    }

    public List<ProductResponse> list() {
        return productRepository.findAll().stream()
                .map(ProductResponse::from)
                .toList();
    }

    @Transactional
    public ProductResponse update(Long id, ProductUpdateRequest req) {
        Product p = findEntity(id);
        p.setName(req.getName());
        p.setPrice(req.getPrice());
        p.setStock(req.getStock());
        p.setDescription(req.getDescription());
        p.setStatus(req.getStatus());

        // JPA 영속성 컨텍스트 때문에 save 호출 안 해도 되지만,
        // Repository 패턴 통일을 위해 호출해도 무방
        productRepository.save(p);

        return ProductResponse.from(p);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
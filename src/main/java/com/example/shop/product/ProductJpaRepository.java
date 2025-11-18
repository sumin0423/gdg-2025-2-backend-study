package com.example.shop.product;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductJpaRepository implements ProductRepository {

    private final EntityManager em;

    @Override
    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("select p from Product p order by p.id desc", Product.class)
                .getResultList();
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            em.persist(product);   // 신규
        } else {
            em.merge(product);     // 수정
        }
    }

    @Override
    public void deleteById(Long id) {
        Product p = findById(id);
        if (p != null) {
            em.remove(p);
        }
    }
}
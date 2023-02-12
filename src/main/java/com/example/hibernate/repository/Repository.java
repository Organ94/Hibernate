package com.example.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Repository
@AllArgsConstructor
public class Repository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<String> getProductName(String name) {
        return entityManager.createQuery("select o.product.productName " +
                "from Customers c " +
                "left join Orders o " +
                "on c.id = o.customerId " +
                "where lower(c.name) = lower(:name)")
                .setParameter("name", name)
                .getResultList();
    }
}

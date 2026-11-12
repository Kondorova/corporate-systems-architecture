
package com.example.cosmetics.service;

import com.example.cosmetics.entity.Product;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductService {

    @PersistenceContext(unitName = "CosmeticsPU")
    private EntityManager em;

    public List<Product> findAll() {
        return em.createQuery(
            "select p from Product p join fetch p.brand order by p.id",
            Product.class
        ).getResultList();
    }

    public Product findById(Integer id) {
        return em.find(Product.class, id);
    }

    public void create(Product product) {
        em.persist(product);
    }

    public Product update(Product product) {
        return em.merge(product);
    }

    public void delete(Integer id) {
        Product product = em.find(Product.class, id);

        if (product != null) {
            em.remove(product);
        }
    }
}

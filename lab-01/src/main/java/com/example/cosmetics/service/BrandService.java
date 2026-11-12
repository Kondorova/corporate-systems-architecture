
package com.example.cosmetics.service;

import com.example.cosmetics.entity.Brand;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BrandService {

    @PersistenceContext(unitName = "CosmeticsPU")
    private EntityManager em;

    public List<Brand> findAll() {
        return em.createQuery(
            "select b from Brand b order by b.name",
            Brand.class
        ).getResultList();
    }

    public Brand findById(Integer id) {
        return em.find(Brand.class, id);
    }

    public void create(Brand brand) {
        em.persist(brand);
    }

    public Brand update(Brand brand) {
        return em.merge(brand);
    }

    public void delete(Integer id) {
        Brand brand = em.find(Brand.class, id);

        if (brand != null) {
            em.remove(brand);
        }
    }
}

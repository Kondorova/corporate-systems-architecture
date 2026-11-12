
package com.example.cosmetics.bean;

import com.example.cosmetics.entity.Brand;
import com.example.cosmetics.entity.Product;
import com.example.cosmetics.service.BrandService;
import com.example.cosmetics.service.ProductService;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("productBean")
@ViewScoped
public class ProductBean implements Serializable {

    @EJB
    private ProductService productService;

    @EJB
    private BrandService brandService;

    private Product product;
    private Integer brandId;

    @PostConstruct
    public void init() {
        prepareNew();
    }

    public List<Product> getProducts() {
        return productService.findAll();
    }

    public List<Brand> getBrands() {
        return brandService.findAll();
    }

    public void prepareNew() {
        product = new Product();
        brandId = null;
    }

    public void edit(Product selected) {
        product = productService.findById(selected.getId());
        brandId = product.getBrand().getId();
    }

    public void save() {
        Brand brand = brandService.findById(brandId);

        if (brand == null) {
            message("Выберите существующий бренд");
            return;
        }

        product.setBrand(brand);

        if (product.getId() == null) {
            productService.create(product);
            message("Товар добавлен");
        } else {
            productService.update(product);
            message("Товар обновлён");
        }

        prepareNew();
    }

    public void delete(Integer id) {
        productService.delete(id);
        message("Товар удалён");
        prepareNew();
    }

    private void message(String text) {
        FacesContext.getCurrentInstance().addMessage(
            null,
            new FacesMessage(text)
        );
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
}

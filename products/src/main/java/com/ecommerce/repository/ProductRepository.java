package com.ecommerce.repository;

import com.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Order products by Name ASC
    List<Product> findAllByOrderByPriceAsc();
    // Find product by Name
    Product findByName(String name);
}

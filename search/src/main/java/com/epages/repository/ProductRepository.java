package com.epages.repository;

import com.epages.entities.Brand;
import com.epages.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Order products by Name ASC
    List<Product> findAllByOrderByPriceAsc();
    // Find product by Name
    Product findByName(String name);
}

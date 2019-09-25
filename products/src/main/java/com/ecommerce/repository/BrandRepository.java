package com.ecommerce.repository;

import com.ecommerce.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    // Order by Name ASC
    List<Brand> findAllByOrderByNameAsc();
    // Find product by Name
    Brand findByName(String name);
}

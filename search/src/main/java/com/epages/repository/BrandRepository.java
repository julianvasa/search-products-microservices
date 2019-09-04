package com.epages.repository;

import com.epages.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    // Order by Name ASC
    List<Brand> findAllByOrderByNameAsc();
}

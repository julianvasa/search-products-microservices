package com.epages.controller;

import com.epages.entities.Brand;
import com.epages.entities.Product;
import com.epages.repository.BrandRepository;
import com.epages.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private ProductRepository prodRepository;
    private BrandRepository brandRepository;

    @Autowired
    public Controller(ProductRepository prodRepository, BrandRepository brandRepository) {
        this.prodRepository = prodRepository;
        this.brandRepository = brandRepository;
    }

    @GetMapping(path = "/brands", produces="application/json")
    public List<Brand> getBrands(){
        return brandRepository.findAllByOrderByNameAsc();
    }

    @GetMapping(path = "/products", produces="application/json")
    public List<Product> getProducts(){
        return prodRepository.findAllByOrderByPriceAsc();
    }
}

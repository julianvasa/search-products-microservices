package com.ecommerce.controller;

import com.ecommerce.entities.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private RestTemplate template;

    @GetMapping(produces="application/json")
    public Brand[] getBrands(){
        return template.getForObject("http://product-service/brands", Brand[].class);
    }
}

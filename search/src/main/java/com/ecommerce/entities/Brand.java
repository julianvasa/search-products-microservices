package com.ecommerce.entities;

import lombok.*;

import java.util.List;

// Constructors, Getters, Setters created by lombok
@Data
public class Brand implements Comparable, Cloneable {
    // Id is an autoincrement Long
    private Long id;
    private String name;
    private List<Product> products;

    @Override
    public int compareTo(Object o) {
        Brand brandToCompare = (Brand) o;
        return this.getName().compareTo(brandToCompare.getName());
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        Brand brand = new Brand();
        brand.setId(this.id);
        brand.setName(this.name);
        brand.setProducts(this.products);
        return brand;
    }
}

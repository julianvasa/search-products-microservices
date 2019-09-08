package com.epages.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

// Constructors, Getters, Setters created by lombok
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "brand")
public class Brand implements Comparable, Cloneable {
    // Id is an autoincrement Long
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Map 1 brand to multiple products
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    // Sort products inside a brand by price ASC
    @OrderBy("price ASC")
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

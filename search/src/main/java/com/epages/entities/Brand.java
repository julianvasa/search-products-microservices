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
public class Brand {
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

}

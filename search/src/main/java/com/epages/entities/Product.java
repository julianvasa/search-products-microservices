package com.epages.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.IOException;

// Constructors, Getters, Setters created by lombok
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product{
    // Id is an autoincrement Long
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    // If event is false/null do not display in the JSON
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonSerialize(using=StringBooleanSerializer.class)
    private Boolean event;

    // Map many products to 1 brand
    @ManyToOne
    // mandatory to put jsonignore here to prevent circular exception
    @JsonIgnore
    @JoinColumn(name = "brand_id")
    private Brand brand;
}

class StringBooleanSerializer extends JsonSerializer<Boolean> {

    // Custom Serializer, if event = false return null else return "ON SALE"
    @Override
    public void serialize(Boolean bool, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeString(bool ? "ON SALE" : null);
    }
}

package com.epages.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.IOException;

// Constructors, Getters, Setters created by lombok
@Entity
@Data
public class Product implements Cloneable, Comparable {
    // Id is an autoincrement Long
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    // If event is false/null do not display in the JSON
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @JsonSerialize(using=StringBooleanSerializer.class)
    @JsonDeserialize(using=StringBooleanDeserializer.class)
    private Boolean event;

    // Map many products to 1 brand
    @ManyToOne
    // mandatory to put jsonignore here to prevent circular exception
    @JsonIgnore
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        super.clone();
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setPrice(this.price);
        product.setEvent(this.event);
        product.setBrand(this.brand);
        return product;
    }

    @Override
    public int compareTo(Object o) {
        return Double.compare(this.price, ((Product) o).getPrice());
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Product or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Product)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Product c = (Product) o;

        // Compare the data members and return accordingly
        return Double.compare(this.price, c.getPrice()) == 0
            && this.id == c.getId()
            && this.name.equals(c.getName())
            && Boolean.compare(this.event, c.getEvent()) == 0;
    }
}

class StringBooleanSerializer extends JsonSerializer<Boolean> {

    // Custom Serializer, if event = false return null else return "ON SALE"
    @Override
    public void serialize(Boolean bool, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeString(bool ? "ON SALE" : null);
    }
}

class StringBooleanDeserializer extends JsonDeserializer<Boolean> {

    // Custom Deserializer, if event = "ON SALE" return true else return false
    @Override
    public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return jsonParser.getValueAsString().equals("ON SALE");
    }
}

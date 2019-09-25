package com.ecommerce;

import com.ecommerce.entities.Brand;
import com.ecommerce.entities.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class TestController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    @Test
    public void whenListingAllProductsAndBrands_thenReturnListSize() {
        try {
            MvcResult res = this.mockMvc.perform(
                get("/brands")
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();
            String content = res.getResponse().getContentAsString();
            JSONArray jsonArray = JsonPath.read(content, "$");
            assertEquals(2, jsonArray.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenListingAllProductsAndBrands_thenBrandsAreSortedASC() {
        try {
            MvcResult res = this.mockMvc.perform(
                get("/brands")
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();
            String content = res.getResponse().getContentAsString();
            Brand[] brands = mapper.readValue(content, Brand[].class);
            Brand[] sortedBrands = brands.clone();
            Arrays.sort(sortedBrands);
            assertArrayEquals(sortedBrands, brands);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenListingAllProductsAndBrands_thenProductBrandShouldBeOmitted() {
        try {
            MvcResult res = this.mockMvc.perform(
                get("/brands")
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();
            String content = res.getResponse().getContentAsString();
            Brand[] brands = mapper.readValue(content, Brand[].class);
            boolean foundBrand = false;

            for (Brand currentBrand : brands) {
                for (Product currentProduct : currentBrand.getProducts()) {
                    if (currentProduct.getBrand() != null) {
                        foundBrand = true;
                        break;
                    }
                }
            }
            assertFalse(foundBrand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenListingAllProductsAndBrands_thenProductInsideBrandShouldBeSortedByPrice() {
        try {
            MvcResult res = this.mockMvc.perform(
                get("/brands")
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();
            String content = res.getResponse().getContentAsString();
            Brand[] brands = mapper.readValue(content, Brand[].class);
            boolean sortedProducts = true;

            for (Brand currentBrand : brands) {
                List<Product> products = currentBrand.getProducts();
                Collections.sort(products);
                if(!products.equals(currentBrand.getProducts())) sortedProducts = false;
            }
            assertTrue(sortedProducts);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

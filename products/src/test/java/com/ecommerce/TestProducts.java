package com.ecommerce;

import com.ecommerce.entities.Product;
import com.ecommerce.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@Transactional
@RunWith(SpringRunner.class)
@DataJpaTest
public class TestProducts {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void setUp(){
        // given
        Product product = new Product();
        product.setName("Last Product");
        product.setPrice(9999);
        testEntityManager.persist(product);
    }

    @Test
    public void whenFindAll_thenReturnProductListSize() {
            assertEquals(17, productRepository.findAll().size());
    }

    @Test
    public void whenFindById_thenReturnProductName() {
        // when
        Product product = productRepository.getOne(1L);

        // then
        assertEquals("Product A", product.getName());
    }

    @Test
    public void whenFindLastProduct_thenReturnProductPrice() {
        // when
        Product product = productRepository.findByName("Last Product");

        // then
        assertEquals(9999, product.getPrice(), 0);
    }

    @Test
    public void whenFindById_thenReturnProductBrandName() {
        // when
        Product product = productRepository.getOne(1L);

        // then
        assertEquals("Brand B", product.getBrand().getName());
    }

    @Test
    public void whenFindLastProduct_thenReturnBrandNull() {
        // when
        Product product = productRepository.findByName("Last Product");

        // then
        assertNull(product.getBrand());
    }
}

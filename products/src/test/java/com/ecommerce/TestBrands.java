package com.ecommerce;

import com.ecommerce.entities.Brand;
import com.ecommerce.repository.BrandRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@Transactional
@RunWith(SpringRunner.class)
@DataJpaTest
public class TestBrands {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void setUp(){
        Brand brand = new Brand();
        brand.setName("Third Brand");
        brand.setProducts(new ArrayList<>());
        testEntityManager.persist(brand);
    }

    @Test
    public void whenFindAll_thenReturnBrandsListSize() {
        assertEquals(3, brandRepository.findAll().size());
    }

    @Test
    public void whenFindById_thenReturnCountProducts() {
        // when
        Brand brand = brandRepository.getOne(1L);

        // then
        assertEquals(8, brand.getProducts().size());
    }

    @Test
    public void whenFindById_thenReturnBrand() {
        // when
        Brand brand = brandRepository.getOne(1L);

        // then
        assertEquals("Brand B", brand.getName());
    }

    @Test
    public void whenFindLastBrand_thenReturnProductsListSize_Zero() {
        // when
        Brand brand = brandRepository.findByName("Third Brand");

        // then
        assertEquals(0, brand.getProducts().size());
    }
}

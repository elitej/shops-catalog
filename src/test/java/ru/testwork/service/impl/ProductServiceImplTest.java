package ru.testwork.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.testwork.exception.ProductNotFoundException;
import ru.testwork.exception.ShopNotFoundException;
import ru.testwork.model.Address;
import ru.testwork.model.Product;
import ru.testwork.model.Shop;
import ru.testwork.repository.ProductRepository;
import ru.testwork.service.ProductService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-test-service.xml")
public class ProductServiceImplTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepositoryMock;

    @Before
    public void setUp() {
        reset(productRepositoryMock);
    }

    @Test
    public void findById_ShouldReturnItemWithTheSpecifiedId() {
        Product product = new Product(2L, "testName");
        when(productRepositoryMock.findOne(2L)).thenReturn(product);

        Product returnedProduct = productService.findById(2L);
        assertEquals(new Long(2), returnedProduct.getId());
        assertEquals("testName", returnedProduct.getName());

        verify(productRepositoryMock, times(1)).findOne(2L);
        verifyNoMoreInteractions(productRepositoryMock);
    }

    @Test(expected = ProductNotFoundException.class)
    public void findById_ShopEntryNotFound_ShouldThrowException() {
        when(productRepositoryMock.findOne(5L)).thenReturn(null);
        productService.findById(5L);
    }
}
package ru.testwork.service.impl;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.testwork.exception.ShopNotFoundException;
import ru.testwork.model.Address;
import ru.testwork.model.Product;
import ru.testwork.model.Shop;
import ru.testwork.repository.ShopRepository;
import ru.testwork.service.ShopService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-test-service.xml")
public class ShopServiceImplTest {

    @Autowired
    ShopService shopService;

    @Autowired
    ShopRepository shopRepositoryMock;

    @Autowired
    Page pageMock;

    @Before
    public void setUp() {
        reset(shopRepositoryMock);
    }

    @Test
    public void findById_ShouldReturnShopWithTheSpecifiedId() {
        Shop shop = new Shop(3L, "testName", new Address("testCity", "testStreet"),
                Arrays.asList(new Product(), new Product()));
        when(shopRepositoryMock.findOne(3L)).thenReturn(shop);

        Shop returnedShop = shopService.findById(3L, true);
        assertEquals(new Long(3), returnedShop.getId());
        assertEquals("testName", returnedShop.getName());
        assertEquals("testCity", returnedShop.getAddress().getCity());
        assertEquals("testStreet", returnedShop.getAddress().getStreet());
        assertEquals(2, returnedShop.getProducts().size());

        verify(shopRepositoryMock, times(1)).findOne(3L);
        verifyNoMoreInteractions(shopRepositoryMock);
    }

    @Test(expected = ShopNotFoundException.class)
    public void findById_ShopEntryNotFound_ShouldThrowException() {
        when(shopRepositoryMock.findOne(5L)).thenReturn(null);
        shopService.findById(5L, true);
    }

    @Test
    public void findByName_ShouldReturnShops() {
        Shop shop1 = new Shop(1L, "testName", new Address("testCity1", "testStreet1"));
        Shop shop2 = new Shop(2L, "testName", new Address("testCity2", "testStreet2"));
        PageRequest request = new PageRequest(0, 10, Sort.Direction.ASC, "id");
        List<Shop> shops = Arrays.asList(shop1, shop2);
        when(shopRepositoryMock.findByName("testName", request)).thenReturn(shops);

        List<Shop> returnedShop = shopService.findByName("testName");
        assertEquals(shops, returnedShop);

        verify(shopRepositoryMock, times(1)).findByName("testName", request);
        verifyNoMoreInteractions(shopRepositoryMock);
    }

    @Test
    public void findForPage_ShouldReturnShopForPage() {
        Shop shop1 = new Shop(1L, "testName", new Address("testCity1", "testStreet1"));
        Shop shop2 = new Shop(2L, "testName", new Address("testCity2", "testStreet2"));
        Shop shop3 = new Shop(3L, "testName", new Address("testCity3", "testStreet3"));
        Shop shop4 = new Shop(4L, "testName", new Address("testCity4", "testStreet4"));
        PageRequest request = new PageRequest(0, 10, Sort.Direction.ASC, "id");
        List<Shop> shops = Arrays.asList(shop1, shop2, shop3, shop4);
        when(pageMock.getContent()).thenReturn(shops);
        when(shopRepositoryMock.findAll(request)).thenReturn(pageMock);

        Page<Shop> shopPages = shopService.findForPage(1);
        assertEquals(shops, shopPages.getContent());

        verify(shopRepositoryMock, times(1)).findAll(request);
        verifyNoMoreInteractions(shopRepositoryMock);
    }
}
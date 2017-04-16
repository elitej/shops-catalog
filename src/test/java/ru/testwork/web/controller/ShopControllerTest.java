package ru.testwork.web.controller;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.testwork.exception.ShopNotFoundException;
import ru.testwork.model.Address;
import ru.testwork.model.Product;
import ru.testwork.model.Shop;
import ru.testwork.service.ShopService;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-test-mvc.xml")
@WebAppConfiguration
public class ShopControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ShopService shopServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        reset(shopServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void showShop_ShouldAddShopToModelAndRenderShopView() throws Exception {
        Product product1 = new Product(5L, "Bread");
        Product product2 = new Product(6L, "Tomato");
        Shop shop = new Shop(1L, "Example Shop", new Address(), Arrays.asList(product1, product2));
        when(shopServiceMock.findById(1, true)).thenReturn(shop);

        mockMvc.perform(get("/shop/{shopId}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("shop"))
                .andExpect(forwardedUrl("/WEB-INF/views/shop.jsp"))
                .andExpect(model().attribute("shop", hasProperty("id", is(1L))))
                .andExpect(model().attribute("shop", hasProperty("name", is("Example Shop"))))
                .andExpect(model().attribute("products", hasSize(2)))
                .andExpect(model().attribute("products", hasItem(
                        allOf(
                                hasProperty("id", is(5L)),
                                hasProperty("name", is("Bread"))))))
                .andExpect(model().attribute("products", hasItem(
                        allOf(
                                hasProperty("id", is(6L)),
                                hasProperty("name", is("Tomato"))))));

        verify(shopServiceMock, times(1)).findById(1, true);
        verifyNoMoreInteractions(shopServiceMock);
    }

    @Test
    public void showShopByName() throws Exception {
        when(shopServiceMock.findByName("A")).thenReturn(Arrays.asList(new Shop(), new Shop()));

        mockMvc.perform(get("/shop/search/{shopName}", "A"))
                .andExpect(status().isOk())
                .andExpect(view().name("shop"))
                .andExpect(forwardedUrl("/WEB-INF/views/shop.jsp"))
                .andExpect(model().attribute("shops", hasSize(2)));
        verify(shopServiceMock, times(1)).findByName("A");
        verifyNoMoreInteractions(shopServiceMock);
    }

    @Test
    public void showShop_ShopEntryNotFound_ShouldRender404View() throws Exception {
        ShopNotFoundException exception = new ShopNotFoundException(1);
        when(shopServiceMock.findById(1, true)).thenThrow(exception);

        mockMvc.perform(get("/shop/{shopId}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("error/404"))
                .andExpect(forwardedUrl("/WEB-INF/views/error/404.jsp"))
                .andExpect(model().attribute("message", exception.getMessage()));

        verify(shopServiceMock, times(1)).findById(1, true);
        verifyNoMoreInteractions(shopServiceMock);
    }

}
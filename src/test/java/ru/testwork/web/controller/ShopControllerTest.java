package ru.testwork.web.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        when(shopServiceMock.findById(1)).thenReturn(new Shop());

        mockMvc.perform(get("/shop/{shopId}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("shop"))
                .andExpect(forwardedUrl("/WEB-INF/views/shop.jsp"));
        // TODO: 12.04.2017 Add checks for shop properties
        verify(shopServiceMock, times(1)).findById(1);
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
        when(shopServiceMock.findById(1)).thenThrow(exception);

        mockMvc.perform(get("/shop/{shopId}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("error/404"))
                .andExpect(forwardedUrl("/WEB-INF/views/error/404.jsp"))
                .andExpect(model().attribute("message", exception.getMessage()));

        verify(shopServiceMock, times(1)).findById(1);
        verifyNoMoreInteractions(shopServiceMock);
    }

}